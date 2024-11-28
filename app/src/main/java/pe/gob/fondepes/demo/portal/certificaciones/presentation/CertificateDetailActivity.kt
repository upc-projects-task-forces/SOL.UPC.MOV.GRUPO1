package pe.gob.fondepes.demo.portal.certificaciones.presentation

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.presentation.classes.Certificate

class CertificateDetailActivity : AppCompatActivity() {
    private lateinit var certificate: Certificate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_certificate_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgBtnBack = findViewById<ImageButton>(R.id.imgBtnBack)
        imgBtnBack.setOnClickListener {
            finish()
        }

        val tempCertificate: Certificate? = intent.getParcelableExtra("certificate")
        if (tempCertificate == null) {
            Toast.makeText(this, "No se pudo obtener el certificado.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        certificate = tempCertificate

        certificate.let {
            findViewById<TextView>(R.id.txtTitleCertDetail).text = it.title

            val txtStatusCertDetail = findViewById<TextView>(R.id.txtStatusCertDetail)
            txtStatusCertDetail.text = it.status
            if (it.status == "FINALIZADO") {
                txtStatusCertDetail.setTextColor(getColor(R.color.green))
            } else {
                txtStatusCertDetail.setTextColor(getColor(R.color.orange))
            }
            findViewById<TextView>(R.id.txtExpirationCertDetail).text = it.expirationDate
            findViewById<TextView>(R.id.txtInstructorCertDetail).text = it.instructor
            findViewById<TextView>(R.id.txtTimeLimitCertDetail).text = it.timeLimit
        }

        val btnDescargar = findViewById<Button>(R.id.btnDownloadCertDetail)
        val btnVerQr = findViewById<Button>(R.id.btnViewQrCertDetail)
        val txtVerQr = findViewById<TextView>(R.id.txtNoteCertDetail)
        val url = certificate.url

        if (url.isEmpty()) {
            btnDescargar.visibility = Button.GONE
            btnVerQr.visibility = Button.GONE
            txtVerQr.visibility = Button.GONE
        } else {
            btnVerQr.visibility = Button.VISIBLE
            txtVerQr.visibility = Button.VISIBLE
            btnVerQr.setOnClickListener {
                showQRCodeModal(url)
            }

            btnDescargar.visibility = Button.VISIBLE
            btnDescargar.setOnClickListener {
                val webView = findViewById<WebView>(R.id.webView)

                // Configuración básica
                webView.settings.javaScriptEnabled = true // Habilita JavaScript si es necesario
                webView.webViewClient =
                    WebViewClient() // Impide que se abra en un navegador externo

                // Carga el enlace
                if (url == "") {
                    Toast.makeText(this, "No existe certificado para mostrar", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    openPdf(url)
                    webView.loadUrl(url)
                }

            }
        }

    }

    private fun showQRCodeModal(url: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_qr)
        val imgQrCode = dialog.findViewById<ImageView>(R.id.imgQrCode)
        val btnCloseQr = dialog.findViewById<Button>(R.id.btnCloseQr)

        val qrBitmap = generateQrCode(url)
        imgQrCode.setImageBitmap(qrBitmap)

        btnCloseQr.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun generateQrCode(url: String): Bitmap? {
        try {
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(
                        x,
                        y,
                        if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
                    )
                }
            }

            return bmp
        } catch (e: WriterException) {
            e.printStackTrace()
            return null
        }
    }

    private fun openPdf(pdfUrl: String) {
        try {
            // Crear un intent con la acción VIEW
            val intent = Intent(Intent.ACTION_VIEW)

            // Configurar el URI del archivo PDF
            intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf")

            // Agregar flags para abrir el selector de aplicaciones
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            // Iniciar el selector
            startActivity(Intent.createChooser(intent, "Abrir PDF con"))
        } catch (e: ActivityNotFoundException) {
            // Manejar el caso donde no hay apps para abrir PDFs
            Toast.makeText(
                this,
                "No tienes aplicaciones para abrir archivos PDF",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
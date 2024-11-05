package pe.gob.fondepes.demo.portal.certificaciones

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.gob.fondepes.demo.portal.certificaciones.classes.Certificate

class CertificateDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_certificate_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val certificate: Certificate? = intent.getParcelableExtra("certificate")
        certificate?.let {
            findViewById<TextView>(R.id.txtTitleCertDetail).text = it.title
            findViewById<TextView>(R.id.txtExpirationCertDetail).text = it.date
        }
    }
}
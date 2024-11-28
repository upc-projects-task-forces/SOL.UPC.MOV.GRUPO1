package pe.gob.fondepes.demo.portal.certificaciones.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.presentation.classes.Certificate

class CertificateAdapter(context: Context, private val certificates: ArrayList<Certificate>) : ArrayAdapter<Certificate>(context, 0, certificates){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val certificate = certificates[position]
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.certificate_item, parent, false)
        view.findViewById<TextView>(R.id.txtTitleCertDetail).text = certificate.title
        view.findViewById<TextView>(R.id.txtDescriptionCertDetail).text = certificate.description
        view.findViewById<TextView>(R.id.txtDateCertDetail).text = certificate.expirationDate
        view.findViewById<TextView>(R.id.txtCertDetailStatus).text = certificate.status
        return view
    }
}
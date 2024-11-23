package pe.gob.fondepes.demo.portal.certificaciones.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.presentation.adapter.CertificateAdapter
import pe.gob.fondepes.demo.portal.certificaciones.presentation.classes.Certificate

class CertificateListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_certificados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView: ListView = view.findViewById(R.id.lvCertification)
        val certificates = arrayListOf(
            Certificate(
                "Liberación de tortugas",
                "Manipulación y liberación de tortugas marinas, aves y otras especies de captura incidental",
                "VIGENTE",
                "07/11/2024",
                "GONZALES, JUAN ANTONIO",
                "07/11/2026"
            ),
            Certificate(
                "Seguridad en faenas de pesca",
                "Seguridad en faenas de pesca y primeros auxilios",
                "VIGENTE",
                "07/11/2024",
                "METZGER, RICARDO MARTIN",
                "07/11/2026"
            )
        )
        val adapter = CertificateAdapter(requireContext(), certificates)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCertificate = certificates[position]
            val intent = Intent(requireContext(), CertificateDetailActivity::class.java).apply {
                putExtra("certificate", selectedCertificate)
            }
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CertificateListFragment()
    }
}
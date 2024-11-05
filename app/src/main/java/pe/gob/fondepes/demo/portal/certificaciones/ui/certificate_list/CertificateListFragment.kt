package pe.gob.fondepes.demo.portal.certificaciones.ui.certificate_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import pe.gob.fondepes.demo.portal.certificaciones.CertificateDetailActivity
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.adapter.CertificateAdapter
import pe.gob.fondepes.demo.portal.certificaciones.classes.Certificate

class CertificateListFragment : Fragment() {
    private lateinit var listView: ListView
    private lateinit var adapter: CertificateAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_certificate_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.lvCertification)

        val certificates = arguments?.getParcelableArrayList<Certificate>("certificates") ?: arrayListOf()

        adapter = CertificateAdapter(requireContext(), certificates)
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
        fun newInstance(certificates: ArrayList<Certificate>): CertificateListFragment {
            val fragment = CertificateListFragment()
            val args = Bundle().apply {
                putParcelableArrayList("certificates", certificates)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
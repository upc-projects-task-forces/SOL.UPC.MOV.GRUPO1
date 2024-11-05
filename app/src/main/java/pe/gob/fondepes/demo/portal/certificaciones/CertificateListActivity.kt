package pe.gob.fondepes.demo.portal.certificaciones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.gob.fondepes.demo.portal.certificaciones.classes.Certificate
import pe.gob.fondepes.demo.portal.certificaciones.ui.certificate_list.CertificateListFragment


class CertificateListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certificate_list)

        val certificates = arrayListOf(
            Certificate("Liberación de tortugas", "Manipulación y liberación de tortugas marinas, aves y otras especies de captura incidental", "05/04/2024"),
            Certificate("Seguridad en faenas de pesca", "Seguridad en faenas de pesca y primeros auxilios", "05/04/2024")
        )

        val fragment = CertificateListFragment.newInstance(certificates)
        supportFragmentManager.beginTransaction()
            .replace(R.id.certification_item_view, fragment)
            .commit()
    }

}
package pe.gob.fondepes.demo.portal.certificaciones

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import pe.gob.fondepes.demo.portal.certificaciones.adapter.NotificationAdapter
import pe.gob.fondepes.demo.portal.certificaciones.data.Notification
import pe.gob.fondepes.demo.portal.certificaciones.data.NotificationState

class NotificationActivity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.menu_motificaciones
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_certificaciones -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("fragment", "CertificadosFragment")
                    startActivity(intent)
                    true
                }
                R.id.menu_motificaciones -> {
                    true
                }
                else -> false
            }
        }


        val notifications = listOf(
            Notification(
                title = "Nueva certificación",
                body = "Seguridad ante derrames de petróleo",
                state = NotificationState.WITH_BUTTON
            ),
            Notification(
                title = "Próxima renovación",
                body = "Certificación Liberación de tortugas",
                state = NotificationState.WITH_ICON
            ),
            Notification(
                title = "Recordatorio",
                body = "Certificación de primeros auxilios expira pronto",
                state = NotificationState.DEFAULT
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NotificationAdapter(notifications)
    }

    private fun loadFragment(fragment: Fragment){
        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.container, fragment)
        tx.commit()
    }
}
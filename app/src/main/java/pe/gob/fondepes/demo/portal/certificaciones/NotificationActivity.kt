package pe.gob.fondepes.demo.portal.certificaciones

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.gob.fondepes.demo.portal.certificaciones.adapter.NotificationAdapter
import pe.gob.fondepes.demo.portal.certificaciones.data.Notification
import pe.gob.fondepes.demo.portal.certificaciones.data.NotificationState

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
}
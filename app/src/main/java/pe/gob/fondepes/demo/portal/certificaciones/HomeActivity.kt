package pe.gob.fondepes.demo.portal.certificaciones

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import pe.gob.fondepes.demo.portal.certificaciones.data.Notification

class HomeActivity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_certificaciones -> {
                    loadFragment(CertificadosFragment())
                    true
                }
                R.id.menu_motificaciones -> {
                    val intent = Intent(this, NotificationActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {false}
            }
        }

        var iconButtonNotification = findViewById<ImageView>(R.id.imageView3)
        iconButtonNotification.setOnClickListener{
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadFragment(fragment: Fragment){
        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.container, fragment)
        tx.commit()
    }
}
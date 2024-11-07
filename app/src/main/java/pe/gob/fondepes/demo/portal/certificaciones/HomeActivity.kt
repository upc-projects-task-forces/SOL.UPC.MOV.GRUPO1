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
                    loadFragment(CertificateListFragment.newInstance())
                    true
                }
                R.id.menu_motificaciones -> {
                    loadFragment(NotificationFragment.newInstance())
                    true
                }
                else -> {false}
            }
        }

        val iconButtonNotification = findViewById<ImageView>(R.id.imageView3)
        iconButtonNotification.setOnClickListener{
            loadFragment(NotificationFragment.newInstance())
        }

        loadFragment(CertificateListFragment.newInstance())
    }

    private fun loadFragment(fragment: Fragment){
        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.container, fragment)
        tx.commit()
    }
}
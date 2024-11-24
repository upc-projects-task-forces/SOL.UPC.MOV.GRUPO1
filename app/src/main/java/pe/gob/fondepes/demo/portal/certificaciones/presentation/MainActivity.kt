package pe.gob.fondepes.demo.portal.certificaciones.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.data.LoginRepository
import pe.gob.fondepes.demo.portal.certificaciones.presentation.di.DependencyProvider

class MainActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        // Usa WindowInsetsController para ocultar la barra de estado y navegación de manera transparente
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.apply {
                // Hacer que la barra de estado y la barra de navegación sean transparentes
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }

        // Configura los colores de las barras a transparente
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        etUsername = findViewById(R.id.editTextText3)
        etPassword = findViewById(R.id.editTextTextPassword)
        etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submit()
                return@setOnEditorActionListener true
            }
            false
        }

        loginRepository = DependencyProvider.provideLoginRepository(this)

        etUsername = findViewById(R.id.editTextText3)
        etPassword = findViewById(R.id.editTextTextPassword)
        etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submit()
                return@setOnEditorActionListener true
            }
            false
        }

        btnLogin = findViewById(R.id.button)

        btnLogin.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        validateCredentials(username, password)
    }


    private fun validateCredentials(username: String, password: String) {
        loginRepository.login(
            username,
            password,
            {
                // Iniciar sesión exitoso, redirige a la siguiente actividad
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            },
            { error ->
                // Error durante el inicio de sesión, muestra un Toast
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }


}
package pe.gob.fondepes.demo.portal.certificaciones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etUsername = findViewById(R.id.editTextText3)
        etPassword = findViewById(R.id.editTextTextPassword)
        btnLogin = findViewById(R.id.button)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            validateCredentials(username, password)
        }
    }



    private fun validateCredentials(username: String, password: String) {
        val correctUsername = "tu_usuario_correcto"
        val correctPassword = "tu_contraseña_correcta"

        if (username == correctUsername && password == correctPassword) {
            // Inicio de sesión exitoso, redirige a la siguiente actividad
            val intent = Intent(this, MainActivity::class.java) // Reemplaza MainActivity con la actividad a la que deseas redirigir
            startActivity(intent)
        } else {
            // Credenciales incorrectas, muestra un Toast
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }


}
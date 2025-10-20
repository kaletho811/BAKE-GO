package com.example.bakego.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bakego.R

class LoginActivity : AppCompatActivity() {
    private val PREFS_NAME = "UserPrefs"

    private lateinit var campCorrLogin: EditText
    private lateinit var campContrLogin: EditText
    private lateinit var btnLogin: Button
    private lateinit var linkRegis: TextView

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        campCorrLogin = findViewById(R.id.correo_ini)
        campContrLogin = findViewById(R.id.pass_ini)
        btnLogin = findViewById(R.id.button)
        linkRegis = findViewById(R.id.Link_regis_login)

        btnLogin.setOnClickListener {
            iniciarSesionLocal()
        }

        linkRegis.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarSesionLocal() {
        val correo = campCorrLogin.text.toString().trim()
        val contrasena = campContrLogin.text.toString().trim()

        if (correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa correo y contraseña.", Toast.LENGTH_SHORT).show()
            return
        }

        val storedPassword = sharedPrefs.getString(correo, "")

        if (storedPassword.isNullOrEmpty()) {
            Toast.makeText(this, "El correo no está registrado.", Toast.LENGTH_LONG).show()
        } else if (storedPassword == contrasena) {
            Toast.makeText(this, "¡Bienvenido! Sesión iniciada.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Contraseña incorrecta.", Toast.LENGTH_SHORT).show()
        }
    }
}
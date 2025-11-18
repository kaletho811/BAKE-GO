package com.example.bakego.Activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView // Importar ImageView para la flecha de retroceso
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bakego.R

class RecPassActivity : AppCompatActivity() {

    private lateinit var campoCorreo: EditText
    private lateinit var btnEnviarCodigo: Button
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rec_pass)

        campoCorreo = findViewById(R.id.camp_corr_rec_cont)
        btnEnviarCodigo = findViewById(R.id.btn_env_cod_rec_cont)
        backButton = findViewById(R.id.ic_back_recpass)
        backButton.setOnClickListener {
            finish()
        }
        btnEnviarCodigo.setOnClickListener {
            val email = campoCorreo.text.toString().trim()
            simularEnvioCodigo(email)
        }
    }

    private fun simularEnvioCodigo(email: String) {
        if (email.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu correo electrónico.", Toast.LENGTH_LONG).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Formato de correo electrónico inválido.", Toast.LENGTH_LONG).show()
            return
        }

        val mensajeExito = "Se ha enviado un código de recuperación a $email. Por favor, revisa tu bandeja de entrada."
        Toast.makeText(this, mensajeExito, Toast.LENGTH_LONG).show()
    }
}
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
    private lateinit var backButton: ImageView // Declaración de la flecha

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asumiendo que el layout se llama activity_rec_pass
        setContentView(R.layout.activity_rec_pass)

        // Enlazar vistas
        campoCorreo = findViewById(R.id.camp_corr_rec_cont)
        btnEnviarCodigo = findViewById(R.id.btn_env_cod_rec_cont)
        backButton = findViewById(R.id.ic_back_recpass) // Inicializar la flecha

        // Configurar el click de la flecha para retroceder
        // finish() cierra esta actividad y regresa a la anterior en la pila (LoginActivity)
        backButton.setOnClickListener {
            finish()
        }

        // Configurar el click del botón
        btnEnviarCodigo.setOnClickListener {
            val email = campoCorreo.text.toString().trim()
            simularEnvioCodigo(email)
        }
    }

    /**
     * Valida el formato del correo y simula el proceso de envío de código.
     * En una aplicación real, esta función contendría la lógica para llamar a un servidor
     * y solicitar el envío del correo de restablecimiento.
     */
    private fun simularEnvioCodigo(email: String) {
        if (email.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu correo electrónico.", Toast.LENGTH_LONG).show()
            return
        }

        // Validación básica de formato de correo (debe contener @ y .)
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Formato de correo electrónico inválido.", Toast.LENGTH_LONG).show()
            return
        }

        // --- Simulación de éxito ---

        // Aquí iría la llamada real a un SDK (como Firebase, Supabase, o tu propio backend).
        val mensajeExito = "Se ha enviado un código de recuperación a $email. Por favor, revisa tu bandeja de entrada."
        Toast.makeText(this, mensajeExito, Toast.LENGTH_LONG).show()

        // En una app real, aquí podrías navegar a una actividad que pida el código después del éxito.
    }
}
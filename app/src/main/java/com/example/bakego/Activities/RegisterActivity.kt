package com.example.bakego.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bakego.R

class RegisterActivity : AppCompatActivity() {

    private val PREFS_NAME = "UserPrefs"

    // CAMPOS DE TEXTO ADICIONALES
    private lateinit var campNom: EditText
    private lateinit var campApellido: EditText
    private lateinit var campTel: EditText
    private lateinit var campDireccion: EditText
    private lateinit var chuloRegis: CheckBox
    private lateinit var linkTermCond: TextView
    private lateinit var campCorrRegis: EditText
    private lateinit var campContrRegis: EditText
    private lateinit var btnRegis: Button

    private lateinit var sharedPrefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // 1. Enlazar todos los campos del activity_register.xml
        campNom = findViewById(R.id.camp_nom)
        campApellido = findViewById(R.id.camp_apellido)
        campCorrRegis = findViewById(R.id.camp_corr_regis)
        campTel = findViewById(R.id.camp_tel)
        campContrRegis = findViewById(R.id.camp_contr_regis)
        campDireccion = findViewById(R.id.camp_direccion)
        chuloRegis = findViewById(R.id.chulo_regis)
        btnRegis = findViewById(R.id.btn_regis)

        linkTermCond = findViewById(R.id.link_term_cond_regis)

        btnRegis.setOnClickListener {
            registrarUsuarioLocal()
        }

        linkTermCond.setOnClickListener {
            val intent = Intent(this, TermCondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registrarUsuarioLocal() {
        val nombre = campNom.text.toString().trim()
        val apellido = campApellido.text.toString().trim()
        val correo = campCorrRegis.text.toString().trim()
        val telefono = campTel.text.toString().trim()
        val contrasena = campContrRegis.text.toString().trim()
        val direccion = campDireccion.text.toString().trim()

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty() || contrasena.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        if (contrasena.length < 6) {
            Toast.makeText(
                this,
                "La contraseña debe tener al menos 6 caracteres.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (!chuloRegis.isChecked) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones.", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPrefs.edit()

        // Guardado de datos con el correo como prefijo
        editor.putString(correo, contrasena)
        editor.putString("${correo}_nombre", nombre)
        editor.putString("${correo}_apellido", apellido)
        editor.putString("${correo}_telefono", telefono)
        editor.putString("${correo}_direccion", direccion)
        editor.putString("last_registered_email", correo) // No es esencial, pero lo mantenemos

        editor.apply()

        Toast.makeText(
            this,
            "¡Registro local exitoso! Por favor, inicia sesión.",
            Toast.LENGTH_LONG
        ).show()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
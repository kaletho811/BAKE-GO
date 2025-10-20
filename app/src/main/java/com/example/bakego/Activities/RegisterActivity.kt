package com.example.bakego.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bakego.R

class RegisterActivity : AppCompatActivity() {

    private val PREFS_NAME = "UserPrefs"

    private lateinit var campCorrRegis: EditText
    private lateinit var campContrRegis: EditText
    private lateinit var btnRegis: Button

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        campCorrRegis = findViewById(R.id.camp_corr_regis)
        campContrRegis = findViewById(R.id.camp_contr_regis)
        btnRegis = findViewById(R.id.btn_regis)

        btnRegis.setOnClickListener {
            registrarUsuarioLocal()
        }
    }

    private fun registrarUsuarioLocal() {
        val correo = campCorrRegis.text.toString().trim()
        val contrasena = campContrRegis.text.toString().trim()

        if (correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "El correo y la contraseña son obligatorios.", Toast.LENGTH_SHORT)
                .show()
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
        val editor = sharedPrefs.edit()

        editor.putString("email", correo)
        editor.putString(correo, contrasena)
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
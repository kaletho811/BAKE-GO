package com.example.bakego.Activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bakego.R
class TermCondActivity : AppCompatActivity() {
    private lateinit var btnAceptarTerm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_cond)

        btnAceptarTerm = findViewById(R.id.btn_acep_term)

        btnAceptarTerm.setOnClickListener {
            finish()
        }
    }
}
package com.example.bakego.Activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bakego.R
import com.example.bakego.Fragments.GestionUsuFragment
import com.example.bakego.Fragments.GestionProdFragment

class GestionAppActivity : AppCompatActivity() {

    private lateinit var btnGestUsu: Button
    private lateinit var btnGestProd: Button

    private val FRAGMENT_CONTAINER_ID = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_app)

        btnGestUsu = findViewById(R.id.btn_gest_usu)
        btnGestProd = findViewById(R.id.btn_gest_prod)

        btnGestUsu.setOnClickListener {
            cargarFragmento(GestionUsuFragment())
        }

        btnGestProd.setOnClickListener {
            cargarFragmento(GestionProdFragment())
        }
    }

    private fun cargarFragmento(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(FRAGMENT_CONTAINER_ID, fragment)
            .addToBackStack(null)
            .commit()
    }
}
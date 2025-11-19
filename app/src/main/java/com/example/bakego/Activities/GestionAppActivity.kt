package com.example.bakego.Activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.bakego.R
import com.example.bakego.Fragments.GestionUsuFragment
import com.example.bakego.Fragments.GestionProdFragment

class GestionAppActivity : AppCompatActivity() {

    private lateinit var btnGestUsu: Button
    private lateinit var btnGestProd: Button

    // Referencias a los contenedores: El de botones y el de Fragments
    private lateinit var buttonContainer: ConstraintLayout
    private lateinit var fragmentContainer: FragmentContainerView

    // ID del contenedor de Fragments
    private val FRAGMENT_CONTAINER_ID = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_app)

        // 1. Inicialización de Vistas y Contenedores
        btnGestUsu = findViewById(R.id.btn_gest_usu)
        btnGestProd = findViewById(R.id.btn_gest_prod)

        // ¡CLAVE! Inicializar los contenedores según el ID que tienen en el XML
        buttonContainer = findViewById(R.id.button_container)
        fragmentContainer = findViewById(R.id.fragment_container)

        // 2. Asignación de Listeners
        btnGestUsu.setOnClickListener {
            cargarFragmento(GestionUsuFragment())
        }

        btnGestProd.setOnClickListener {
            cargarFragmento(GestionProdFragment())
        }
    }

    private fun cargarFragmento(fragment: Fragment) {
        // 3. Ajuste de Visibilidad: Oculta los botones y muestra el contenedor de Fragments
        buttonContainer.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        // 4. Transacción del Fragment
        supportFragmentManager.beginTransaction()
            .replace(FRAGMENT_CONTAINER_ID, fragment)
            .addToBackStack(null)
            .commit()
        }
}
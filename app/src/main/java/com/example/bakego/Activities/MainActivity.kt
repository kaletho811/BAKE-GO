package com.example.bakego.Activities

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView // ¡Importar CardView!
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bakego.Fragments.PerfilFragment
import com.example.bakego.Fragments.ProductFragment
import com.example.bakego.R

class MainActivity : AppCompatActivity() {
    private lateinit var icPerfilMain: ImageView
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var contentMainLayout: ConstraintLayout

    // Declaraciones de las CardView
    private lateinit var cardLimon: CardView
    private lateinit var cardFresa: CardView
    private lateinit var cardGelatinaMosaico: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        icPerfilMain = findViewById(R.id.ic_perfil_main)
        fragmentContainer = findViewById(R.id.fragment_container)
        contentMainLayout = findViewById(R.id.content_main_layout)

        // 1. Enlazar las CardView
        cardLimon = findViewById(R.id.card_limon)
        cardFresa = findViewById(R.id.card_fresa)
        cardGelatinaMosaico = findViewById(R.id.card_gelatina_mosaico)

        // Manejo del click del perfil (existente)
        icPerfilMain.setOnClickListener {
            mostrarFragmentoPerfil()
        }

        // 2. Asignar OnClickListener a cada CardView con su ID de MockData
        // --- ASIGNACIÓN DE CLICKS ---

        cardLimon.setOnClickListener {
            // Usar el ID que definiste en MockData
            abrirFragmentoProducto("limon_cup")
        }

        cardFresa.setOnClickListener {
            // Usar el ID que definiste en MockData
            abrirFragmentoProducto("fresa_cake")
        }

        cardGelatinaMosaico.setOnClickListener {
            // Usar el ID que definiste en MockData
            abrirFragmentoProducto("MOSAICO_cake")
        }

        // --- FIN ASIGNACIÓN DE CLICKS ---

        // Lógica de retroceso (existente)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                    icPerfilMain.postDelayed({
                        if (supportFragmentManager.backStackEntryCount == 0) {
                            fragmentContainer.visibility = View.GONE
                            contentMainLayout.visibility = View.VISIBLE
                        }
                    }, 50)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun mostrarFragmentoPerfil() {
        contentMainLayout.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        val perfilFragment = PerfilFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, perfilFragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * Función pública para navegar al ProductFragment usando el ID del producto.
     * @param productId El ID único del producto (ej: "limon_cup").
     */
    fun abrirFragmentoProducto(productId: String) {
        contentMainLayout.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        val productFragment = ProductFragment()

        // Empaquetar el ID del producto para pasarlo al Fragment
        val bundle = Bundle().apply {
            // La clave "product_id" es la que ProductFragment está esperando
            putString("product_id", productId)
        }
        productFragment.arguments = bundle

        // Iniciar la transacción del Fragmento
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, productFragment)
            .addToBackStack("MainToProduct")
            .commit()
    }
}
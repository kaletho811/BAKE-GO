package com.example.bakego.Activities

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bakego.Fragments.PerfilFragment
import com.example.bakego.Fragments.ProductFragment
import com.example.bakego.Fragments.CarritoFragment
import com.example.bakego.R

class MainActivity : AppCompatActivity() {
    private lateinit var icPerfilMain: ImageView
    private lateinit var icCarritoMain: ImageView

    // FIX: Cambiamos 'private' a 'internal' para permitir el acceso desde fragmentos
    // en otros paquetes dentro del mismo módulo de la aplicación.
    internal lateinit var fragmentContainer: FrameLayout
    internal lateinit var contentMainLayout: ConstraintLayout

    // Declaraciones de las CardView
    private lateinit var cardLimon: CardView
    private lateinit var cardFresa: CardView
    private lateinit var cardGelatinaMosaico: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        icPerfilMain = findViewById(R.id.ic_perfil_main)
        icCarritoMain = findViewById(R.id.imageView11)
        fragmentContainer = findViewById(R.id.fragment_container)
        contentMainLayout = findViewById(R.id.content_main_layout)

        // Enlazar las CardView
        cardLimon = findViewById(R.id.card_limon)
        cardFresa = findViewById(R.id.card_fresa)
        cardGelatinaMosaico = findViewById(R.id.card_gelatina_mosaico)

        // --- SOLUCIÓN AL PROBLEMA DE PANTALLA BLANCA (MÉTODO ROBUSTO) ---
        // Listener que se dispara CADA VEZ que cambia la pila de fragmentos.
        // Asegura que volvemos a la vista principal cuando la pila está vacía.
        supportFragmentManager.addOnBackStackChangedListener {
            // Si la pila está vacía (hemos regresado a la raíz), mostramos el contenido principal.
            if (supportFragmentManager.backStackEntryCount == 0) {
                contentMainLayout.visibility = View.VISIBLE
                fragmentContainer.visibility = View.GONE
            }
        }
        // -----------------------------------------------------------------

        // Manejo del click del perfil
        icPerfilMain.setOnClickListener {
            mostrarFragmentoPerfil()
        }

        // Asignar OnClickListener a la ImageView del carrito
        icCarritoMain.setOnClickListener {
            mostrarFragmentoCarrito()
        }

        // Asignar OnClickListener a cada CardView con su ID de MockData
        cardLimon.setOnClickListener {
            abrirFragmentoProducto("limon_cup")
        }

        cardFresa.setOnClickListener {
            abrirFragmentoProducto("fresa_cake")
        }

        cardGelatinaMosaico.setOnClickListener {
            abrirFragmentoProducto("MOSAICO_cake")
        }

        // Manejo del botón físico/virtual de retroceso del dispositivo.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    // Si hay fragmentos, usamos el pop estándar, que activa el listener de arriba.
                    supportFragmentManager.popBackStack()
                } else {
                    // Si no hay fragmentos, salimos de la actividad.
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun mostrarFragmentoPerfil() {
        contentMainLayout.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        val perfilFragment = PerfilFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, perfilFragment)
            .addToBackStack("MainToPerfil") // Usamos un tag explícito
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
            putString("product_id", productId)
        }
        productFragment.arguments = bundle

        // Iniciar la transacción del Fragmento
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, productFragment)
            .addToBackStack("MainToProduct")
            .commit()
    }

    // Función para mostrar el CarritoFragment
    private fun mostrarFragmentoCarrito() {
        contentMainLayout.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        val carritoFragment = CarritoFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, carritoFragment)
            .addToBackStack("MainToCarrito") // Un tag para esta transacción
            .commit()
    }
}
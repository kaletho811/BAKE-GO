package com.example.bakego.Activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bakego.Fragments.PerfilFragment
import androidx.fragment.app.FragmentManager
import com.example.bakego.R
import android.widget.FrameLayout
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bakego.Fragments.ProductFragment // <-- ¡IMPORTANTE: Asegúrate de tener esta importación!

class MainActivity : AppCompatActivity() {
    private lateinit var icPerfilMain: ImageView
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var contentMainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        icPerfilMain = findViewById(R.id.ic_perfil_main)
        fragmentContainer = findViewById(R.id.fragment_container)
        contentMainLayout = findViewById(R.id.content_main_layout)

        icPerfilMain.setOnClickListener {
            mostrarFragmentoPerfil()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                    icPerfilMain.postDelayed({
                        // Después de 50ms (para dar tiempo a la transición), revisa la pila
                        if (supportFragmentManager.backStackEntryCount == 0) {
                            fragmentContainer.visibility = View.GONE
                            contentMainLayout.visibility = View.VISIBLE
                        }
                    }, 50)

                } else {
                    // Si la pila está vacía, permite que la Activity se cierre
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
     * Función pública para navegar al ProductFragment desde cualquier parte de la app.
     * @param productName El nombre del producto (ej: "POSTRE DE LIMON").
     * @param productPrice El precio del producto (ej: "$$$$$$ c/u").
     */
    fun abrirFragmentoProducto(productName: String, productPrice: String) {
        contentMainLayout.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        val productFragment = ProductFragment()

        // 1. Empaquetar los datos para pasarlos al Fragment
        val bundle = Bundle().apply {
            putString("product_name", productName)
            putString("product_price", productPrice)
        }
        productFragment.arguments = bundle

        // 2. Iniciar la transacción del Fragmento
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, productFragment)
            .addToBackStack("MainToProduct") // Agregamos a la pila para poder usar el botón de retroceso
            .commit()
    }
}
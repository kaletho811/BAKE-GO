package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bakego.R
import com.example.bakego.Data.CarritoManager


class CarritoFragment : Fragment() {

    // Componentes del layout
    private lateinit var backButton: android.widget.ImageView
    private lateinit var listaProductosTextView: TextView
    private lateinit var totalTextView: TextView
    private lateinit var comprarButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inicializar Vistas
        backButton = view.findViewById(R.id.ic_back_carrito)
        listaProductosTextView = view.findViewById(R.id.tv_lista_productos)
        totalTextView = view.findViewById(R.id.texto_total_carrito)
        comprarButton = view.findViewById(R.id.btn_add_car) // ID del botón "Comprar"

        // 2. Establecer Listeners

        // Listener del botón de retroceso
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Listener del botón COMPRAR
        comprarButton.setOnClickListener {
            if (CarritoManager.productos.isNotEmpty()) {
                // LLAMADA A LA FUNCIÓN DE NAVEGACIÓN
                navegarAConfirmacion()
            } else {
                Toast.makeText(requireContext(), "El carrito está vacío. Añade productos para comprar.", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Mostrar la lista inicial de productos
        actualizarVistaCarrito()
    }

    /**
     * Función para navegar a ConfirmacionFragment.
     */
    private fun navegarAConfirmacion() {
        // 1. Instancia el fragmento de destino
        val confirmacionFragment = ConfirmacionFragment()

        // 2. Ejecuta la transacción del fragmento
        parentFragmentManager.beginTransaction()
            // ❗ IMPORTANTE: Asegúrate que R.id.fragment_container sea el ID de tu contenedor ❗
            .replace(R.id.fragment_container, confirmacionFragment)
            // Permite volver al carrito al presionar el botón de atrás
            .addToBackStack(null)
            .commit()
    }


    /**
     * Función para recalcular el total y actualizar el TextView de la lista.
     */
    private fun actualizarVistaCarrito() {
        var listaTexto = ""
        var total = 0.0

        for (producto in CarritoManager.productos) {
            val subtotal = producto.precioUnitario * producto.cantidad
            listaTexto += "${producto.nombre} (x${producto.cantidad}) - $${"%.2f".format(subtotal)}\n"
            total += subtotal
        }

        // 1. Actualiza el TextView principal con la lista o el mensaje de vacío
        if (listaTexto.isEmpty()) {
            listaProductosTextView.text = "El carrito está vacío."
        } else {
            listaProductosTextView.text = listaTexto
        }

        // 2. Actualiza el TextView del total
        totalTextView.text = "TOTAL: $${"%.2f".format(total)}"
    }

    override fun onResume() {
        super.onResume()
        // Actualizar la vista cada vez que el fragmento vuelve a estar en primer plano
        actualizarVistaCarrito()
    }
}
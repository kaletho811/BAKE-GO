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
// 🌟 Importación del CarritoManager que ahora está en el paquete Data
import com.example.bakego.Data.CarritoManager
// ❗ ELIMINA LA CLASE DE DATOS ProductoCarrito DUPLICADA DE AQUÍ ❗


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
        comprarButton = view.findViewById(R.id.btn_add_car) // Asegúrate que el ID sea correcto

        // 2. Establecer Listeners

        // Listener del botón de retroceso
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Listener del botón COMPRAR
        comprarButton.setOnClickListener {
            if (CarritoManager.productos.isNotEmpty()) {
                // LLAMADA A LA FUNCIÓN DE NAVEGACIÓN
                // Debes asegurarte de que ConfirmacionFragment existe y de que el ID del contenedor es R.id.fragment_container
                navegarAConfirmacion()
            } else {
                Toast.makeText(requireContext(), "El carrito está vacío. Añade productos para comprar.", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Mostrar la lista inicial de productos
        actualizarVistaCarrito()
    }

    /**
     * Función para navegar a ConfirmacionFragment. (Asumiendo que existe)
     */
    private fun navegarAConfirmacion() {
        // Debes implementar ConfirmacionFragment o usar un fragmento existente
        // val confirmacionFragment = ConfirmacionFragment()

        // Asumiendo que el contenedor de fragmentos en tu actividad es R.id.fragment_container
        /*
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, confirmacionFragment)
            .addToBackStack(null)
            .commit()
        */
        Toast.makeText(requireContext(), "Navegando a confirmación de compra...", Toast.LENGTH_SHORT).show()
    }


    /**
     * Función para recalcular el total y actualizar el TextView de la lista.
     * 🌟 Ahora lee del CarritoManager importado.
     */
    private fun actualizarVistaCarrito() {
        var listaTexto = ""
        var total = 0.0

        for (producto in CarritoManager.productos) { // 🌟 Accediendo al Manager
            val subtotal = producto.precioUnitario * producto.cantidad
            // Formato básico de lista: Nombre (xCantidad) - $Subtotal
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


    // ❗ ELIMINA el object CarritoManager interno y el companion object ❗
    // (Ya no son necesarios porque usamos el CarritoManager del paquete Data)


    override fun onResume() {
        super.onResume()
        // 🌟 Actualizar la vista cada vez que el fragmento vuelve a estar en primer plano
        actualizarVistaCarrito()
    }
}
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

        backButton = view.findViewById(R.id.ic_back_carrito)
        listaProductosTextView = view.findViewById(R.id.tv_lista_productos)
        totalTextView = view.findViewById(R.id.texto_total_carrito)
        comprarButton = view.findViewById(R.id.btn_add_car)

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        comprarButton.setOnClickListener {
            if (CarritoManager.productos.isNotEmpty()) {
                navegarAConfirmacion()
            } else {
                Toast.makeText(requireContext(), "El carrito está vacío. Añade productos para comprar.", Toast.LENGTH_SHORT).show()
            }
        }

        actualizarVistaCarrito()
    }

    private fun navegarAConfirmacion() {
        val confirmacionFragment = ConfirmacionFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, confirmacionFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun actualizarVistaCarrito() {
        var listaTexto = ""
        var total = 0.0

        for (producto in CarritoManager.productos) {
            val subtotal = producto.precioUnitario * producto.cantidad
            listaTexto += "${producto.nombre} (x${producto.cantidad}) - $${"%.2f".format(subtotal)}\n"
            total += subtotal
        }

        if (listaTexto.isEmpty()) {
            listaProductosTextView.text = "El carrito está vacío."
        } else {
            listaProductosTextView.text = listaTexto
        }

        totalTextView.text = "TOTAL: $${"%.2f".format(total)}"
    }

    override fun onResume() {
        super.onResume()
        actualizarVistaCarrito()
    }
}
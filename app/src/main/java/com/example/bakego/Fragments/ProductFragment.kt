package com.example.bakego.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.bakego.R
import com.example.bakego.Data.MockData
import com.example.bakego.Data.Product

class ProductFragment : Fragment() {

    private lateinit var backButton: ImageView
    private lateinit var btnAddCar: Button
    private lateinit var tvProductTitle: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvProductDescription: TextView
    private lateinit var imgProduct: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        // Enlace de vistas
        backButton = view.findViewById(R.id.ic_back_product)
        btnAddCar = view.findViewById(R.id.btn_add_car)
        tvProductTitle = view.findViewById(R.id.tv_product_title)
        tvProductPrice = view.findViewById(R.id.tv_product_price)
        tvProductDescription = view.findViewById(R.id.tv_product_description)
        // Asegúrate de que este ID (R.id.img_product) exista en tu fragment_product.xml
        imgProduct = view.findViewById(R.id.img_product)

        // 1. Configurar la flecha de retroceso
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // 2. Configurar el botón de añadir al carrito - CORRECCIÓN DE STRING LITERAL
        btnAddCar.setOnClickListener {
            val productName = tvProductTitle.text.toString().replace(":", "").trim()

            // Usamos getString() para obtener la plantilla de string del XML
            val toastMessage = getString(R.string.producto_anadido_carrito, productName)

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }

        // 3. Cargar datos del producto de forma dinámica
        cargarDetallesProducto(arguments)

        return view
    }

    private fun cargarDetallesProducto(args: Bundle?) {
        val productId = args?.getString("product_id")

        if (productId.isNullOrEmpty()) {
            tvProductTitle.text = "ERROR:"
            // Usa recursos de strings para mensajes de error si es posible
            tvProductDescription.text = "Producto no encontrado."
            btnAddCar.isEnabled = false
            return
        }

        val product: Product? = MockData.getProductById(productId)

        if (product == null) {
            tvProductTitle.text = "ERROR:"
            // Usa recursos de strings para mensajes de error si es posible
            tvProductDescription.text = "Producto con ID '$productId' no existe."
            btnAddCar.isEnabled = false
            return
        }

        // Actualizar la interfaz de usuario con los datos del producto
        tvProductTitle.text = product.name + ":"
        tvProductPrice.text = product.price
        tvProductDescription.text = product.description
        imgProduct.setImageResource(product.imageResId)
    }
}
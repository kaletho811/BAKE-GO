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
import com.example.bakego.Activities.MainActivity

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
        imgProduct = view.findViewById(R.id.img_product)

        // 1. Configurar la flecha de retroceso
        backButton.setOnClickListener {
            // Esto realiza la acción de "pop" de la pila de fragmentos,
            // regresando automáticamente a MainActivity (que estaba debajo).
            parentFragmentManager.popBackStack()
        }

        // 2. Configurar el botón de añadir al carrito
        btnAddCar.setOnClickListener {
            val productName = tvProductTitle.text.toString().replace(":", "").trim()

            // Asumiendo que tienes un recurso de string para el mensaje de Toast:
            // Ejemplo: <string name="producto_anadido_carrito">¡%1$s añadido al carrito!</string>
            val toastMessage = getString(R.string.producto_anadido_carrito, productName)

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }

        // 3. Cargar datos del producto de forma dinámica
        cargarDetallesProducto(arguments)

        return view
    }

    /**
     * Carga los detalles del producto usando el 'product_id' pasado en los argumentos.
     */
    private fun cargarDetallesProducto(args: Bundle?) {
        // Obtiene el ID del producto que se pasó desde MainActivity
        val productId = args?.getString("product_id")

        if (productId.isNullOrEmpty()) {
            tvProductTitle.text = "ERROR:"
            tvProductDescription.text = "ID de producto no recibido."
            btnAddCar.isEnabled = false
            return
        }

        // Busca el producto en la fuente de datos simulada
        val product: Product? = MockData.getProductById(productId)

        if (product == null) {
            tvProductTitle.text = "ERROR:"
            tvProductDescription.text = "Producto con ID '$productId' no existe en la base de datos."
            btnAddCar.isEnabled = false
            return
        }

        // Actualiza la interfaz de usuario con los datos del producto
        tvProductTitle.text = product.name + ":"
        tvProductPrice.text = product.price
        tvProductDescription.text = product.description
        imgProduct.setImageResource(product.imageResId)
    }
}
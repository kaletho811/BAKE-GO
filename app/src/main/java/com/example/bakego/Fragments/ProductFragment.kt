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
import com.example.bakego.Data.ProductoCarrito
import com.example.bakego.Data.CarritoManager // 🌟 Importación del CarritoManager

class ProductFragment : Fragment() {

    private lateinit var backButton: ImageView
    private lateinit var btnAddCar: Button
    private lateinit var tvProductTitle: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvProductDescription: TextView
    private lateinit var imgProduct: ImageView

    private var _currentProduct: Product? = null

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

        // Configurar la flecha de retroceso
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // LÓGICA DE AÑADIR AL CARRITO
        btnAddCar.setOnClickListener {
            _currentProduct?.let { product ->
                // Lógica para asignar precio (mantenida)
                val priceDouble = when (product.id) {
                    "limon_cup" -> 3.00
                    "fresa_cake" -> 5.50
                    "MOSAICO_cake" -> 4.25
                    else -> 1.00
                }

                // Aquí se crea el ProductoCarrito
                val productoParaCarrito = ProductoCarrito(
                    id = product.id,
                    nombre = product.name,
                    precioUnitario = priceDouble,
                    cantidad = 1
                )

                // 🌟🌟🌟 CAMBIO CLAVE: Llamar al CarritoManager
                CarritoManager.agregarProducto(productoParaCarrito)

                Toast.makeText(
                    requireContext(),
                    getString(R.string.producto_anadido_carrito, product.name),
                    Toast.LENGTH_SHORT
                ).show()

            } ?: run {
                Toast.makeText(context, "Error: Producto no cargado.", Toast.LENGTH_SHORT).show()
            }
        }

        cargarDetallesProducto(arguments)

        return view
    }

    // --- SOLUCIÓN ALTERNATIVA FORZADA ---
    override fun onDestroyView() {
        super.onDestroyView()

        if (activity is MainActivity) {
            val mainActivity = activity as MainActivity
            if (mainActivity.supportFragmentManager.backStackEntryCount <= 1) {
                // Asumiendo que estas propiedades están definidas en MainActivity
                mainActivity.contentMainLayout.visibility = View.VISIBLE
                mainActivity.fragmentContainer.visibility = View.GONE
            }
        }
    }
    // ------------------------------------


    /**
     * Carga los detalles del producto usando el 'product_id' pasado en los argumentos.
     */
    private fun cargarDetallesProducto(args: Bundle?) {
        val productId = args?.getString("product_id")

        if (productId.isNullOrEmpty()) {
            tvProductTitle.text = "ERROR:"
            tvProductDescription.text = "ID de producto no recibido."
            btnAddCar.isEnabled = false
            return
        }

        val product: Product? = MockData.getProductById(productId)

        if (product == null) {
            tvProductTitle.text = "ERROR:"
            tvProductDescription.text = "Producto con ID '$productId' no existe en la base de datos."
            btnAddCar.isEnabled = false
            return
        }

        // GUARDAR EL PRODUCTO CARGADO
        _currentProduct = product

        // Actualiza la interfaz de usuario con los datos del producto
        tvProductTitle.text = product.name + ":"
        tvProductPrice.text = product.price
        tvProductDescription.text = product.description
        imgProduct.setImageResource(product.imageResId)
    }
}
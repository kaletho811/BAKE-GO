package com.example.bakego.Data

/**
 * Clase de datos para representar un producto en el carrito.
 */
data class ProductoCarrito(
    val id: String,         // ID único del producto (ej: "limon_cup")
    val nombre: String,     // Nombre del producto
    val precioUnitario: Double, // Precio por unidad
    var cantidad: Int       // Cantidad de este producto en el carrito
)

// --- GESTOR DEL CARRITO INTEGRADO EN ESTE MISMO ARCHIVO ---

/**
 * Objeto Singleton para manejar la lista de productos del carrito de forma centralizada.
 * Es accesible desde ProductFragment y CarritoFragment.
 */
object CarritoManager {
    // La lista que contendrá los productos en el carrito
    val productos = mutableListOf<ProductoCarrito>()

    /**
     * Agrega un producto al carrito. Si ya existe, incrementa la cantidad.
     */
    fun agregarProducto(nuevoProducto: ProductoCarrito) {
        val productoExistente = productos.find { it.id == nuevoProducto.id }

        if (productoExistente != null) {
            // Si ya existe, solo incrementa la cantidad
            productoExistente.cantidad += nuevoProducto.cantidad
        } else {
            // Si no existe, añádelo a la lista
            productos.add(nuevoProducto)
        }
    }

    /**
     * Opcional: Función para vaciar el carrito.
     */
    fun vaciarCarrito() {
        productos.clear()
    }
}
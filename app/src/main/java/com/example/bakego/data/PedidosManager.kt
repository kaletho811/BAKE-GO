package com.example.bakego.Data

import android.content.Context
import android.content.SharedPreferences
import com.example.bakego.Fragments.ConfirmacionFragment

// Clase simple para representar un pedido
data class Pedido(
    val numero: Int,
    val postres: String, // Nombres de postres separados por coma
    val direccion: String
)

object PedidosManager {
    // Clave para la lista de pedidos guardados (usaremos un String grande separado por '|')
    private const val PEDIDOS_KEY = "PedidosList"
    // Clave para el contador del número de pedido
    private const val PEDIDO_COUNTER_KEY = "PedidoCounter"
    private const val PREFS_NAME = "AppPedidosPrefs"

    private lateinit var sharedPrefs: SharedPreferences

    fun initialize(context: Context) {
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Guarda un nuevo pedido y actualiza el contador.
     */
    fun guardarNuevoPedido(postres: String, direccion: String) {
        // 1. Obtener el número de pedido actual y el nuevo
        val currentCounter = sharedPrefs.getInt(PEDIDO_COUNTER_KEY, 0)
        val nuevoNumero = currentCounter + 1

        // 2. Crear la representación del nuevo pedido como un String: "Numero|Postres|Direccion"
        val nuevoPedidoString = "$nuevoNumero|$postres|$direccion"

        // 3. Obtener la lista actual de pedidos y añadir el nuevo
        val listaActual = sharedPrefs.getString(PEDIDOS_KEY, "") ?: ""
        val nuevaLista = if (listaActual.isEmpty()) {
            nuevoPedidoString
        } else {
            "$listaActual\n$nuevoPedidoString" // Usamos '\n' como separador entre pedidos
        }

        // 4. Guardar la nueva lista y el nuevo contador
        sharedPrefs.edit().apply {
            putString(PEDIDOS_KEY, nuevaLista)
            putInt(PEDIDO_COUNTER_KEY, nuevoNumero)
            apply()
        }
    }

    /**
     * Obtiene todos los pedidos guardados.
     * @return Lista de objetos Pedido.
     */
    fun obtenerPedidos(): List<Pedido> {
        val listaString = sharedPrefs.getString(PEDIDOS_KEY, "") ?: return emptyList()
        if (listaString.isEmpty()) return emptyList()

        return listaString.split('\n').mapNotNull { pedidoLine ->
            val partes = pedidoLine.split('|')
            if (partes.size == 3) {
                try {
                    Pedido(
                        numero = partes[0].toInt(),
                        postres = partes[1],
                        direccion = partes[2]
                    )
                } catch (e: NumberFormatException) {
                    null // Si el número falla, ignoramos esta línea
                }
            } else {
                null // Si el formato es incorrecto, ignoramos esta línea
            }
            }
       }
}
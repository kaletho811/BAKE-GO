package com.example.bakego.Data
import com.example.bakego.R

// Si la clase Product está en un archivo separado (Product.kt), la dejas ahí.
// Si está en este mismo archivo, asegúrate de mantenerla aquí.
data class Product(
    val id: String,
    val name: String,
    val description: String,
    // Este campo ahora contendrá el precio formateado en COP, ej: "$ 3.000 c/u"
    val price: String,
    val imageResId: Int // Recurso de la imagen (ej: R.drawable.postre_limon)
)

// Este es el object MockData que necesitas actualizar
object MockData {
    // Mapa que simula la base de datos de productos
    val products = mapOf(
        "limon_cup" to Product(
            id = "limon_cup",
            name = "POSTRE DE LIMON",
            description = "Unos deliciosos postres individuales en vasitos transparentes, perfectos para cualquier ocasión. Cada uno tiene una base de galleta triturada, seguida de una cremosa y suave capa de mousse de limón. Para finalizar, están delicadamente adornados con ralladura de limón fresco, dándoles un toque vibrante de color y un aroma inigualable. La combinación de la textura crujiente de la base y la ligereza del mousse crea una experiencia irresistible para el paladar.",
            // 🌟 CORRECCIÓN: Precio de exhibición en formato COP
            price = "$ 3.000 c/u",
            imageResId = R.drawable.limon
        ),
        "fresa_cake" to Product(
            id = "fresa_cake",
            name = "TARTA DE FRESA",
            description = "Una tarta clásica con una base esponjosa, crema pastelera suave y cubierta con fresas frescas y un glaseado brillante.",
            // 🌟 CORRECCIÓN: Precio de exhibición en formato COP
            price = "$ 5.500 c/u",
            imageResId = R.drawable.postre_fresa
        ),
        "MOSAICO_cake" to Product(
            id = "MOSAICO_cake",
            name = "GELATINA MOSAICO",
            description = "Una gelatina clásica y colorida elaborada con cubos de gelatinas de sabores mezclados en una base cremosa. Su textura suave y su combinación de colores la convierten en un postre fresco, ligero y perfecto para cualquier ocasión. Ideal para quienes disfrutan de sabores dulces y una presentación divertida.",
            // 🌟 CORRECCIÓN: Precio de exhibición en formato COP
            price = "$ 4.250 c/u",
            imageResId = R.drawable.gelatinamosaico
        )
    )

    fun getProductById(id: String): Product? {
        return products[id]
    }
}
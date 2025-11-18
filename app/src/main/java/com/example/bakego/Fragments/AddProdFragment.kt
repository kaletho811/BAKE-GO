package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bakego.R

class AddProdFragment : Fragment() {

    // Declaración de variables para los elementos de UI
    private lateinit var icFlechaAtras: ImageView
    private lateinit var editIdProd: EditText
    private lateinit var editNombreProd: EditText
    private lateinit var editCantidadProd: EditText
    private lateinit var editPrecioProd: EditText
    private lateinit var editDescProd: EditText
    private lateinit var spinnerVisible: Spinner
    private lateinit var btnGuardarProd: Button
    private lateinit var cardImagenProd: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_prod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        icFlechaAtras = view.findViewById(R.id.ic_flecha_add_prod)
        editIdProd = view.findViewById(R.id.edit_id_prod)
        editNombreProd = view.findViewById(R.id.edit_nombre_prod)
        editCantidadProd = view.findViewById(R.id.edit_cantidad_prod)
        editPrecioProd = view.findViewById(R.id.edit_precio_prod)
        editDescProd = view.findViewById(R.id.edit_desc_prod)
        spinnerVisible = view.findViewById(R.id.spinner_visible)
        btnGuardarProd = view.findViewById(R.id.btn_guardar_prod)
        cardImagenProd = view.findViewById(R.id.card_imagen_prod)
        configurarSpinner()

        icFlechaAtras.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        // Botón de Subir/Cambiar Imagen
        cardImagenProd.setOnClickListener {
            Toast.makeText(requireContext(), "Abriendo selector de imágenes...", Toast.LENGTH_SHORT).show()
        }
        btnGuardarProd.setOnClickListener {
            guardarProducto()
        }
    }

    private fun configurarSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.opciones_visible,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerVisible.adapter = adapter
    }

    private fun guardarProducto() {
        val id = editIdProd.text.toString().trim()
        val nombre = editNombreProd.text.toString().trim()
        val cantidad = editCantidadProd.text.toString().trim()
        val precio = editPrecioProd.text.toString().trim()
        val descripcion = editDescProd.text.toString().trim()
        val visibilidad = spinnerVisible.selectedItem.toString()

        if (id.isEmpty() || nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, completa los campos requeridos.", Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(
            requireContext(),
            "Producto '$nombre' (ID: $id) guardado con éxito. Visible: $visibilidad",
            Toast.LENGTH_LONG
        ).show()
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}
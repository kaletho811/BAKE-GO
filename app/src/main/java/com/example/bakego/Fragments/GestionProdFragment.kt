package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView // 👈 ¡Importante!
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bakego.R

class GestionProdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asegúrate de que este es el layout correcto para este Fragmento
        return inflater.inflate(R.layout.fragment_gestion_prod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de los botones de gestión (tu código original)
        val btnVerProd: Button = view.findViewById(R.id.btn_ver_prod)
        val btnEditProd: Button = view.findViewById(R.id.btn_edit_prod)
        val btnElimProd: Button = view.findViewById(R.id.btn_elim_prod)
        val btnAddProd: Button = view.findViewById(R.id.btn_add_prod)

        // 1. Obtener la referencia de la ImageView (la flecha)
        val ivAtras: ImageView = view.findViewById(R.id.imageView13) // Revisa que este ID sea correcto

        // 2. Lógica del botón de Volver Atrás
        ivAtras.setOnClickListener {
            // Utilizamos requireActivity().supportFragmentManager para acceder al Manager
            // y popBackStack() para volver al Fragmento/Activity anterior en la pila.
            requireActivity().supportFragmentManager.popBackStack()
            // Esto automáticamente te llevará de vuelta a GestionAppActivity,
            // y debería mostrar los botones grandes.
        }

        // ... (Tu lógica original para los botones de gestión) ...
        btnVerProd.setOnClickListener {
            Toast.makeText(requireContext(), "Cargando listado de productos...", Toast.LENGTH_SHORT).show()
        }
        // ... (resto de listeners) ...
    }
}
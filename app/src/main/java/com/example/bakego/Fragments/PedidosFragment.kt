package com.example.bakego.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.bakego.R

class PedidosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla la vista usando el layout creado
        val view = inflater.inflate(R.layout.fragment_pedidos, container, false)

        // Enlaza el icono de flecha atrás
        val backButton: ImageView = view.findViewById(R.id.ic_back_pedidos)

        // Implementa la funcionalidad para regresar al fragmento anterior
        backButton.setOnClickListener {
            // Esto quita el PedidosFragment de la pila y muestra el PerfilFragment
            parentFragmentManager.popBackStack()
        }

        return view
    }
}
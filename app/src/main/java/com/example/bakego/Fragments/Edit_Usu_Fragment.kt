package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.bakego.R

class EditUsuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asegúrate de que R.layout.fragment_edit_usu es correcto
        return inflater.inflate(R.layout.fragment_edit__usu_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Funcionalidad de la flecha de volver atrás (Vuelve a GestionUsuFragment)
        val ivAtras: ImageView = view.findViewById(R.id.ic_flecha_edit_usu)

        ivAtras.setOnClickListener {
            // Vuelve al Fragmento Padre (GestionUsuFragment)
            parentFragmentManager.popBackStack()
            }
        }
}
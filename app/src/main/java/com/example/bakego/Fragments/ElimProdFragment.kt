package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.bakego.R

class ElimProdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asegúrate de crear el layout: fragment_elim_prod.xml
        return inflater.inflate(R.layout.fragment_elim_prod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Asume que la flecha de volver atrás tiene el ID 'ic_flecha_elim_prod' en tu XML
        val ivAtras: ImageView? = view.findViewById(R.id.ic_flecha_elim_prod)

        ivAtras?.setOnClickListener {
            // Vuelve al Fragmento Padre (GestionProdFragment)
            parentFragmentManager.popBackStack()
            }
        }
}
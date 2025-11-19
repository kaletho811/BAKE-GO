package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.bakego.R

class VerProdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asegúrate de crear el layout: fragment_ver_prod.xml
        return inflater.inflate(R.layout.fragment_ver_prod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Asumiendo que el ID de la flecha en tu layout de Ver Producto es 'ic_flecha_ver_prod'
        // Si no existe, debes crearlo en fragment_ver_prod.xml
        val ivAtras: ImageView? = view.findViewById(R.id.ic_flecha_ver_prod)

        ivAtras?.setOnClickListener {
            // Volver al Fragmento Padre (GestionProdFragment)
            parentFragmentManager.popBackStack()
            }
        }
}
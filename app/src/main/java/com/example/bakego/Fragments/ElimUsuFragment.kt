package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.bakego.R

// Asegúrate de que esta clase coincida con el nombre que usaste en el XML (tools:context)
class ElimUsuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para la eliminación de usuarios (fragment_elim_usu.xml)
        return inflater.inflate(R.layout.fragment_elim_usu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Funcionalidad de la flecha de volver atrás
        // El ID de la flecha en fragment_elim_usu.xml es: ic_flecha_elim_usu
        val ivAtras: ImageView = view.findViewById(R.id.ic_flecha_elim_usu)

        ivAtras.setOnClickListener {
            // Usamos parentFragmentManager para volver al Fragmento Padre (GestionUsuFragment)
            parentFragmentManager.popBackStack()
        }

        // Aquí iría el código para inicializar el RecyclerView y la lógica de eliminación.
       }
}
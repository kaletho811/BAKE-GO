package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.bakego.R

class VerUsuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ver_usu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Funcionalidad de la flecha de volver atrás
        // ID de la flecha en fragment_ver_usu.xml es: ic_flecha_ver_usu
        val ivAtras: ImageView = view.findViewById(R.id.ic_flecha_ver_usu)

        ivAtras.setOnClickListener {
            // Utilizamos el FragmentManager del Fragmento hijo para volver atrás.
            // Esto sacará VerUsuFragment de la pila y regresará a GestionUsuFragment.
            parentFragmentManager.popBackStack()
        }

        // Aquí iría el código para inicializar el RecyclerView y la búsqueda.
        }
}
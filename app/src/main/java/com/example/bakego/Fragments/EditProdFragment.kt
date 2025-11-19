package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.bakego.R

class EditProdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para la edición de productos (fragment_edit_prod.xml)
        return inflater.inflate(R.layout.fragment_edit_prod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Funcionalidad de la flecha de volver atrás
        // El ID de la flecha en tu XML es: ic_flecha_edit_prod
        val ivAtras: ImageView = view.findViewById(R.id.ic_flecha_edit_prod)

        ivAtras.setOnClickListener {
            // Usamos parentFragmentManager para volver al Fragmento Padre (GestionProdFragment)
            parentFragmentManager.popBackStack()
        }

        // Aquí iría el código para inicializar el RecyclerView y la lógica de edición.
        }
}
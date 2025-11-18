package com.example.bakego.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bakego.R

class GestionProdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestion_prod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVerProd: Button = view.findViewById(R.id.btn_ver_prod)
        val btnEditProd: Button = view.findViewById(R.id.btn_edit_prod)
        val btnElimProd: Button = view.findViewById(R.id.btn_elim_prod)
        val btnAddProd: Button = view.findViewById(R.id.btn_add_prod)

        btnVerProd.setOnClickListener {
            Toast.makeText(requireContext(), "Cargando listado de productos...", Toast.LENGTH_SHORT).show()
        }

        btnEditProd.setOnClickListener {
            Toast.makeText(requireContext(), "Abriendo formulario de edición...", Toast.LENGTH_SHORT).show()
        }

        btnElimProd.setOnClickListener {
            Toast.makeText(requireContext(), "Preparando eliminación de productos...", Toast.LENGTH_SHORT).show()
        }

        btnAddProd.setOnClickListener {
            Toast.makeText(requireContext(), "Abriendo formulario para añadir producto...", Toast.LENGTH_SHORT).show()
        }
    }
}
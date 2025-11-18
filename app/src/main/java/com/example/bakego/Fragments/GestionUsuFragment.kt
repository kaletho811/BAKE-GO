package com.example.bakego.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.bakego.R

class GestionUsuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestion_usu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVerUsu: Button = view.findViewById(R.id.btn_ver_usu)
        val btnEditUsu: Button = view.findViewById(R.id.btn_edit_usu)
        val btnElimUsu: Button = view.findViewById(R.id.btn_elim_usu)

        btnVerUsu.setOnClickListener {
            Toast.makeText(requireContext(), "Abriendo Ver Usuarios...", Toast.LENGTH_SHORT).show()
        }

        btnEditUsu.setOnClickListener {
            Toast.makeText(requireContext(), "Abriendo Editar Usuarios...", Toast.LENGTH_SHORT).show()
        }

        btnElimUsu.setOnClickListener {
            Toast.makeText(requireContext(), "Abriendo Eliminar Usuarios...", Toast.LENGTH_SHORT).show()
        }
    }
}
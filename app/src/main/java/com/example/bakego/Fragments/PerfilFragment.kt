package com.example.bakego.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bakego.R
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.Button
import android.widget.TextView


class PerfilFragment : Fragment() {
    private val PREFS_NAME = "UserPrefs"
    private lateinit var nomPerf: TextView
    private lateinit var apePerf: TextView
    private lateinit var corrPerf: TextView
    private lateinit var telPerf: TextView
    private lateinit var direPerf: TextView
    private lateinit var btnEditarPerfil: Button
    private lateinit var sharedPrefs: SharedPreferences

    private var currentUserEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        sharedPrefs = requireActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        currentUserEmail = sharedPrefs.getString("CURRENT_USER_EMAIL", null)

        // Enlazar TextViews
        nomPerf = view.findViewById(R.id.nom_perf)
        apePerf = view.findViewById(R.id.ape_perf)
        corrPerf = view.findViewById(R.id.corr_perf)
        telPerf = view.findViewById(R.id.tel_perf)
        direPerf = view.findViewById(R.id.dire_perf)
        btnEditarPerfil = view.findViewById(R.id.btn_editar_perfil)

        cargarDatosUsuario()

        btnEditarPerfil.setOnClickListener {
            irAEditarPerfil()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        cargarDatosUsuario()
    }

    private fun cargarDatosUsuario() {
        if (currentUserEmail == null) {
            corrPerf.text = "Error: Usuario no identificado"
            return
        }

        val nombre = sharedPrefs.getString("${currentUserEmail}_nombre", "Nombre no guardado")
        val apellido = sharedPrefs.getString("${currentUserEmail}_apellido", "Apellido no guardado")
        val telefono = sharedPrefs.getString("${currentUserEmail}_telefono", "Teléfono no guardado")
        val direccion = sharedPrefs.getString("${currentUserEmail}_direccion", "Dirección no guardada")

        nomPerf.text = "Nombre: $nombre"
        apePerf.text = "Apellido: $apellido"
        corrPerf.text = "Correo: $currentUserEmail"
        telPerf.text = "Teléfono: $telefono"
        direPerf.text = "Dirección: $direccion"
    }

    private fun irAEditarPerfil() {

        parentFragmentManager.beginTransaction()
            .replace(R.id.main, EditPerfilFragment())
            .addToBackStack("PerfilToEdit")
            .commit()
    }
}
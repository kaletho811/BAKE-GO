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
import android.widget.ImageView // Importación necesaria

class PerfilFragment : Fragment() {
    private val PREFS_NAME = "UserPrefs"
    private lateinit var nomPerf: TextView
    private lateinit var apePerf: TextView
    private lateinit var corrPerf: TextView
    private lateinit var telPerf: TextView
    private lateinit var direPerf: TextView
    private lateinit var btnEditarPerfil: Button
    private lateinit var btnPedidos: Button
    private lateinit var backButton: ImageView
    private lateinit var sharedPrefs: SharedPreferences

    private var currentUserEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        sharedPrefs = requireActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        currentUserEmail = sharedPrefs.getString("CURRENT_USER_EMAIL", null)

        nomPerf = view.findViewById(R.id.nom_perf)
        apePerf = view.findViewById(R.id.ape_perf)
        corrPerf = view.findViewById(R.id.corr_perf)
        telPerf = view.findViewById(R.id.tel_perf)
        direPerf = view.findViewById(R.id.dire_perf)
        btnEditarPerfil = view.findViewById(R.id.btn_editar_perfil)
        btnPedidos = view.findViewById(R.id.btn_pedidos_perf)
        backButton = view.findViewById(R.id.ic_back_perfil)

        cargarDatosUsuario()

        btnEditarPerfil.setOnClickListener {
            irAEditarPerfil()
        }

        btnPedidos.setOnClickListener {
            irAPedidos()
        }

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        // --------------------------------------

        return view
    }

    override fun onResume() {
        super.onResume()
        currentUserEmail = sharedPrefs.getString("CURRENT_USER_EMAIL", null)
        cargarDatosUsuario()
    }

    private fun cargarDatosUsuario() {
        if (currentUserEmail.isNullOrEmpty()) {
            nomPerf.text = "NOMBRE: "
            apePerf.text = "APELLIDO: "
            corrPerf.text = "CORREO: (No logueado)"
            telPerf.text = "TELÉFONO: "
            direPerf.text = "DIRECCIÓN: "
            return
        }

        val nombre = sharedPrefs.getString("${currentUserEmail}_nombre", "")
        val apellido = sharedPrefs.getString("${currentUserEmail}_apellido", "")
        val telefono = sharedPrefs.getString("${currentUserEmail}_telefono", "")
        val direccion = sharedPrefs.getString("${currentUserEmail}_direccion", "")

        nomPerf.text = "NOMBRE: $nombre"
        apePerf.text = "APELLIDO: $apellido"
        corrPerf.text = "CORREO: $currentUserEmail"
        telPerf.text = "TELÉFONO: $telefono"
        direPerf.text = "DIRECCIÓN: $direccion"
    }

    private fun irAEditarPerfil() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, EditPerfilFragment())
            .addToBackStack("PerfilToEdit")
            .commit()
    }

    private fun irAPedidos() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PedidosFragment())
            .addToBackStack("PerfilToPedidos")
            .commit()
    }
}
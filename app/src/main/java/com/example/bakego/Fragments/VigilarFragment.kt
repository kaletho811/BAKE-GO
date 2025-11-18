package com.example.bakego.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.bakego.R

class VigilarFragment : Fragment() {
    private val PREFS_NAME = "UserPrefs"
    private val FRAGMENT_CONTAINER_ID = R.id.fragment_container
    private lateinit var icCasa: ImageView
    private lateinit var estVigilar: TextView
    private lateinit var telVigilar: TextView
    private lateinit var direVigilar: TextView
    private lateinit var timeVigilar: TextView

    private lateinit var sharedPrefs: SharedPreferences
    private var currentUserEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vigilar, container, false)

        sharedPrefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        currentUserEmail = sharedPrefs.getString("CURRENT_USER_EMAIL", null)

        icCasa = view.findViewById(R.id.imageView12)
        estVigilar = view.findViewById(R.id.est_vigilar)
        telVigilar = view.findViewById(R.id.tel_vigilar)
        direVigilar = view.findViewById(R.id.dire_vigilar)
        timeVigilar = view.findViewById(R.id.time_vigilar)

        cargarDatosPedido()

        icCasa.setOnClickListener {
            navigateToMainScreen()
        }

        return view
    }

    private fun cargarDatosPedido() {
        val estado = "Pendiente (En preparación)"
        val telefonoRepartidor = "300 123 4567"
        val tiempoEstimado = "25 - 35 minutos"

        estVigilar.text = getString(R.string.estado) + ": " + estado
        telVigilar.text = getString(R.string.telefono_vigi) + " " + telefonoRepartidor
        timeVigilar.text = getString(R.string.tiempo_de_espera) + ": " + tiempoEstimado


        if (currentUserEmail.isNullOrEmpty()) {
            direVigilar.text = getString(R.string.direccion_vigi) + " (Error: Usuario no identificado)"
            return
        }

        val direccionUsuario = sharedPrefs.getString("${currentUserEmail}_direccion", "Dirección no registrada")

        direVigilar.text = getString(R.string.direccion_vigi) + " " + direccionUsuario
    }

    private fun navigateToMainScreen() {
        parentFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
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

    // Constantes
    private val PREFS_NAME = "UserPrefs"
    private val FRAGMENT_CONTAINER_ID = R.id.fragment_container

    // Componentes del layout
    private lateinit var icCasa: ImageView
    private lateinit var estVigilar: TextView
    private lateinit var telVigilar: TextView
    private lateinit var direVigilar: TextView
    private lateinit var timeVigilar: TextView

    // Datos del usuario y del pedido
    private lateinit var sharedPrefs: SharedPreferences
    private var currentUserEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vigilar, container, false)

        // Inicializar SharedPreferences
        sharedPrefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        currentUserEmail = sharedPrefs.getString("CURRENT_USER_EMAIL", null)

        // 1. Enlazar componentes del layout
        icCasa = view.findViewById(R.id.imageView12)
        estVigilar = view.findViewById(R.id.est_vigilar)
        telVigilar = view.findViewById(R.id.tel_vigilar)
        direVigilar = view.findViewById(R.id.dire_vigilar)
        timeVigilar = view.findViewById(R.id.time_vigilar)

        // 2. Cargar y mostrar los datos del pedido
        cargarDatosPedido()

        // 3. Configurar el listener para volver a la pantalla principal
        icCasa.setOnClickListener {
            navigateToMainScreen()
        }

        return view
    }

    /**
     * Carga y simula los datos del pedido en los TextViews.
     */
    private fun cargarDatosPedido() {
        // --- 1. Datos Fijos o Simulados del Pedido ---

        // Estado del pedido (simulado)
        val estado = "Pendiente (En preparación)"
        // Teléfono del repartidor (simulado, se asume que este string ya tiene el prefijo de la etiqueta)
        val telefonoRepartidor = "300 123 4567"
        // Tiempo de espera (simulado, se asume que este string ya tiene el prefijo de la etiqueta)
        val tiempoEstimado = "25 - 35 minutos"

        // Asignar los valores simulados
        estVigilar.text = getString(R.string.estado) + ": " + estado
        telVigilar.text = getString(R.string.telefono_vigi) + " " + telefonoRepartidor
        timeVigilar.text = getString(R.string.tiempo_de_espera) + ": " + tiempoEstimado


        // --- 2. Dirección del Usuario (Leída de SharedPreferences) ---
        if (currentUserEmail.isNullOrEmpty()) {
            direVigilar.text = getString(R.string.direccion_vigi) + " (Error: Usuario no identificado)"
            return
        }

        val direccionUsuario = sharedPrefs.getString("${currentUserEmail}_direccion", "Dirección no registrada")

        // Asignar la dirección del usuario
        direVigilar.text = getString(R.string.direccion_vigi) + " " + direccionUsuario
    }

    /**
     * Navega a la pantalla principal (MainActivity content) cerrando todos los fragmentos.
     */
    private fun navigateToMainScreen() {
        // Limpia toda la pila de retroceso de fragmentos.
        parentFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
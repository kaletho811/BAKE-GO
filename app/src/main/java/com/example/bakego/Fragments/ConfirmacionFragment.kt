package com.example.bakego.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bakego.R
import com.example.bakego.Data.CarritoManager

class ConfirmacionFragment : Fragment() {

    private val PREFS_NAME = "UserPrefs"
    // Componentes de datos
    private lateinit var nomConf: TextView
    private lateinit var apeConf: TextView
    private lateinit var corrConf: TextView
    private lateinit var telConf: TextView
    private lateinit var direConf: TextView
    private lateinit var btnEditConf: ImageButton
    // Componentes de pago y acción
    private lateinit var rgMediosPago: RadioGroup
    private lateinit var btnConfDatos: Button

    private lateinit var backButton: ImageView

    private lateinit var sharedPrefs: SharedPreferences
    private var currentUserEmail: String? = null

    // El ID de tu contenedor de Fragments en MainActivity
    private val FRAGMENT_CONTAINER_ID = R.id.fragment_container

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_confirmacion, container, false)

        sharedPrefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        currentUserEmail = sharedPrefs.getString("CURRENT_USER_EMAIL", null)

        // 1. Enlazar las TextViews de datos
        nomConf = view.findViewById(R.id.nom_conf)
        apeConf = view.findViewById(R.id.ape_conf)
        corrConf = view.findViewById(R.id.corr_conf)
        telConf = view.findViewById(R.id.tel_conf)
        direConf = view.findViewById(R.id.dire_conf)
        btnEditConf = view.findViewById(R.id.btn_edit_conf)

        backButton = view.findViewById(R.id.ic_back_confirmacion)

        // 2. Enlazar los componentes de acción (Pagos y Confirmar)
        rgMediosPago = view.findViewById(R.id.rg_medios_pago)
        btnConfDatos = view.findViewById(R.id.btn_conf_datos) // ID del botón "Confirmar"

        // 3. Cargar los datos del usuario
        cargarDatosConfirmacion()

        // 4. Configurar listeners

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnEditConf.setOnClickListener {
            navigateToEditPerfil()
        }

        btnConfDatos.setOnClickListener {
            procesarConfirmacion()
        }

        return view
    }

    /**
     * Función que lee los datos del usuario de SharedPreferences y los muestra.
     */
    private fun cargarDatosConfirmacion() {
        if (currentUserEmail.isNullOrEmpty()) {
            nomConf.text = getString(R.string.nombre_conf) + " (Error)"
            corrConf.text = getString(R.string.correo_conf) + " (No logueado)"
            // ... otros mensajes de error
            return
        }

        val nombre = sharedPrefs.getString("${currentUserEmail}_nombre", "")
        val apellido = sharedPrefs.getString("${currentUserEmail}_apellido", "")
        val telefono = sharedPrefs.getString("${currentUserEmail}_telefono", "")
        val direccion = sharedPrefs.getString("${currentUserEmail}_direccion", "")

        // Muestra los datos (asumiendo que tus strings tienen formato como "Etiqueta: ")
        nomConf.text = getString(R.string.nombre_conf) + " " + nombre
        apeConf.text = getString(R.string.apellido_conf) + " " + apellido
        corrConf.text = getString(R.string.correo_conf) + " " + currentUserEmail
        telConf.text = getString(R.string.telefono_conf) + " " + telefono
        direConf.text = getString(R.string.direccion_conf) + " " + direccion
    }

    /**
     * Función que maneja la navegación al fragmento de edición de perfil.
     */
    private fun navigateToEditPerfil() {
        val editPerfilFragment = EditPerfilFragment()

        parentFragmentManager.beginTransaction()
            .replace(FRAGMENT_CONTAINER_ID, editPerfilFragment)
            .addToBackStack("ConfirmacionToEditPerfil")
            .commit()
    }

    /**
     * Función que valida, procesa la confirmación de la orden y navega a VigilarFragment.
     */
    private fun procesarConfirmacion() {
        val selectedId = rgMediosPago.checkedRadioButtonId

        if (selectedId == -1) {
            Toast.makeText(requireContext(), "Por favor, selecciona un medio de pago.", Toast.LENGTH_LONG).show()
            return
        }

        // 1. Simular la finalización de la orden
        Toast.makeText(requireContext(), "¡Orden Confirmada! Dirigiendo a seguimiento...", Toast.LENGTH_LONG).show()

        // 2. Limpiar el carrito de compras
        CarritoManager.productos.clear()

        // 3. ❗ MODIFICADO: Navegar a VigilarFragment y limpiar la pila anterior ❗
        val vigilarFragment = VigilarFragment()

        // Esta línea limpia la pila de fragmentos (Carrito, Confirmacion)
        // y reemplaza el actual por VigilarFragment.
        parentFragmentManager.beginTransaction()
            .replace(FRAGMENT_CONTAINER_ID, vigilarFragment)
            .addToBackStack(null) // Opcional: añade Vigilar a la pila si quieres que Back te lleve al inicio.
            .commit()
    }
}
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
import com.example.bakego.Data.PedidosManager

class ConfirmacionFragment : Fragment() {

    private val PREFS_NAME = "UserPrefs"
    private lateinit var nomConf: TextView
    private lateinit var apeConf: TextView
    private lateinit var corrConf: TextView
    private lateinit var telConf: TextView
    private lateinit var direConf: TextView
    private lateinit var btnEditConf: ImageButton
    private lateinit var rgMediosPago: RadioGroup
    private lateinit var btnConfDatos: Button
    private lateinit var backButton: ImageView
    private lateinit var sharedPrefs: SharedPreferences
    private var currentUserEmail: String? = null

    private val FRAGMENT_CONTAINER_ID = R.id.fragment_container

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_confirmacion, container, false)

        sharedPrefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        currentUserEmail = sharedPrefs.getString("CURRENT_USER_EMAIL", null)

        nomConf = view.findViewById(R.id.nom_conf)
        apeConf = view.findViewById(R.id.ape_conf)
        corrConf = view.findViewById(R.id.corr_conf)
        telConf = view.findViewById(R.id.tel_conf)
        direConf = view.findViewById(R.id.dire_conf)
        btnEditConf = view.findViewById(R.id.btn_edit_conf)

        backButton = view.findViewById(R.id.ic_back_confirmacion)

        rgMediosPago = view.findViewById(R.id.rg_medios_pago)
        btnConfDatos = view.findViewById(R.id.btn_conf_datos)

        cargarDatosConfirmacion()

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

    private fun cargarDatosConfirmacion() {
        if (currentUserEmail.isNullOrEmpty()) {
            nomConf.text = getString(R.string.nombre_conf) + " (Error)"
            corrConf.text = getString(R.string.correo_conf) + " (No logueado)"
            apeConf.text = getString(R.string.apellido_conf)
            telConf.text = getString(R.string.telefono_conf)
            direConf.text = getString(R.string.direccion_conf)
            return
        }

        val nombre = sharedPrefs.getString("${currentUserEmail}_nombre", "")
        val apellido = sharedPrefs.getString("${currentUserEmail}_apellido", "")
        val telefono = sharedPrefs.getString("${currentUserEmail}_telefono", "")
        val direccion = sharedPrefs.getString("${currentUserEmail}_direccion", "")

        nomConf.text = getString(R.string.nombre_conf) + " " + nombre
        apeConf.text = getString(R.string.apellido_conf) + " " + apellido
        corrConf.text = getString(R.string.correo_conf) + " " + currentUserEmail
        telConf.text = getString(R.string.telefono_conf) + " " + telefono
        direConf.text = getString(R.string.direccion_conf) + " " + direccion
    }

    private fun navigateToEditPerfil() {
        val editPerfilFragment = EditPerfilFragment()

        parentFragmentManager.beginTransaction()
            .replace(FRAGMENT_CONTAINER_ID, editPerfilFragment)
            .addToBackStack("ConfirmacionToEditPerfil")
            .commit()
    }

    private fun procesarConfirmacion() {
        val selectedId = rgMediosPago.checkedRadioButtonId

        if (selectedId == -1) {
            Toast.makeText(requireContext(), "Por favor, selecciona un medio de pago.", Toast.LENGTH_LONG).show()
            return
        }

        val textoDireccionCompleto = direConf.text.toString()

        val etiquetaDireccion = getString(R.string.direccion_conf) + " "
        val direccionEnvio = textoDireccionCompleto.replace(etiquetaDireccion, "").trim()
        val nombresPostres = CarritoManager.productos.joinToString { it.nombre }

        if (nombresPostres.isEmpty()) {
            Toast.makeText(requireContext(), "El carrito está vacío. No se puede confirmar el pedido.", Toast.LENGTH_LONG).show()
            return
        }
        PedidosManager.guardarNuevoPedido(nombresPostres, direccionEnvio)
        Toast.makeText(requireContext(), "¡Orden Confirmada! Pedido enviado a: $direccionEnvio", Toast.LENGTH_LONG).show()

        CarritoManager.productos.clear()

        val pedidosFragment = PedidosFragment()

        parentFragmentManager.beginTransaction()
            .replace(FRAGMENT_CONTAINER_ID, pedidosFragment)
            .addToBackStack(null)
            .commit()
        }
}
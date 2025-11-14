package com.example.bakego.Fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bakego.R

class EditPerfilFragment : Fragment() {
    private val PREFS_NAME = "UserPrefs"
    private lateinit var sharedPrefs: SharedPreferences
    private var currentUserEmail: String? = null

    private lateinit var campNomEditPerf: EditText
    private lateinit var campApellidoEditPerf: EditText
    private lateinit var campCorrEditPerf: EditText
    private lateinit var campTelEditPerf: EditText
    private lateinit var campDireccionEditPerf: EditText
    private lateinit var campContrEditPerf: EditText
    private lateinit var btnGuardarEdit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_perfil, container, false)

        sharedPrefs = requireActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        currentUserEmail = sharedPrefs.getString("CURRENT_USER_EMAIL", null)

        campNomEditPerf = view.findViewById(R.id.camp_nom_edit_perf)
        campApellidoEditPerf = view.findViewById(R.id.camp_apellido_edit_perf)
        campCorrEditPerf = view.findViewById(R.id.camp_corr_edit_perf)
        campTelEditPerf = view.findViewById(R.id.camp_tel_edit_perf)
        campDireccionEditPerf = view.findViewById(R.id.camp_direccion_edit_perf)
        campContrEditPerf = view.findViewById(R.id.camp_contr_edit_perf)
        btnGuardarEdit = view.findViewById(R.id.btn_guardar_edit)

        cargarDatosExistentes()

        btnGuardarEdit.setOnClickListener {
            guardarCambios()
        }

        return view
    }

    private fun cargarDatosExistentes() {
        if (currentUserEmail == null) return

        val nombre = sharedPrefs.getString("${currentUserEmail}_nombre", "")
        val apellido = sharedPrefs.getString("${currentUserEmail}_apellido", "")
        val telefono = sharedPrefs.getString("${currentUserEmail}_telefono", "")
        val direccion = sharedPrefs.getString("${currentUserEmail}_direccion", "")

        campNomEditPerf.setText(nombre)
        campApellidoEditPerf.setText(apellido)
        campCorrEditPerf.setText(currentUserEmail)
        campCorrEditPerf.isEnabled = false
        campTelEditPerf.setText(telefono)
        campDireccionEditPerf.setText(direccion)

        campContrEditPerf.setText("")
    }

    private fun guardarCambios() {
        if (currentUserEmail == null) {
            Toast.makeText(context, "Error: No hay usuario para guardar.", Toast.LENGTH_LONG).show()
            return
        }

        val nuevoNombre = campNomEditPerf.text.toString().trim()
        val nuevoApellido = campApellidoEditPerf.text.toString().trim()
        val nuevoTelefono = campTelEditPerf.text.toString().trim()
        val nuevaDireccion = campDireccionEditPerf.text.toString().trim()
        val nuevaContrasena = campContrEditPerf.text.toString().trim()

        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoTelefono.isEmpty() || nuevaDireccion.isEmpty()) {
            Toast.makeText(context, "Todos los campos (excepto contraseña) son obligatorios.", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPrefs.edit()

        editor.putString("${currentUserEmail}_nombre", nuevoNombre)
        editor.putString("${currentUserEmail}_apellido", nuevoApellido)
        editor.putString("${currentUserEmail}_telefono", nuevoTelefono)
        editor.putString("${currentUserEmail}_direccion", nuevaDireccion)

        if (nuevaContrasena.isNotEmpty()) {
            if (nuevaContrasena.length < 6) {
                Toast.makeText(context, "La nueva contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show()
                return
            }
            editor.putString(currentUserEmail, nuevaContrasena)
        }

        editor.apply()

        Toast.makeText(context, "Perfil actualizado exitosamente.", Toast.LENGTH_SHORT).show()
        parentFragmentManager.popBackStack()
    }
}
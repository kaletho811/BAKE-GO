package com.example.bakego.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentContainerView
import com.example.bakego.R

class GestionUsuFragment : Fragment() {

    // ID del contenedor donde se cargarán los sub-Fragments (debe existir en el XML)
    private val SUB_FRAGMENT_CONTAINER_ID = R.id.sub_fragment_container

    // Referencia al ConstraintLayout que contiene los botones Ver/Editar/Eliminar
    private lateinit var buttonMenuContainer: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestion_usu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inicialización de Vistas
        val btnVerUsu: Button = view.findViewById(R.id.btn_ver_usu)
        val btnEditUsu: Button = view.findViewById(R.id.btn_edit_usu)
        val btnElimUsu: Button = view.findViewById(R.id.btn_elim_usu)
        val ivAtras: ImageView = view.findViewById(R.id.imageView10)

        buttonMenuContainer = view.findViewById(R.id.button_menu_usu)

        // ------------------------------------------------------------------
        // LÓGICA DE NAVEGACIÓN
        // ------------------------------------------------------------------

        // Botón VER USUARIO: Carga VerUsuFragment
        btnVerUsu.setOnClickListener {
            cargarSubFragmento(VerUsuFragment())
        }

        // Botón EDITAR USUARIO: Carga EditUsuFragment
        btnEditUsu.setOnClickListener {
            cargarSubFragmento(EditUsuFragment())
        }

        // 🚨 CAMBIO AQUÍ: Botón ELIMINAR USUARIO -> Carga ElimUsuFragment
        btnElimUsu.setOnClickListener {
            cargarSubFragmento(ElimUsuFragment())
        }

        // Flecha de VOLVER ATRÁS (Vuelve a GestionAppActivity)
        ivAtras.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // ------------------------------------------------------------------
        // LÓGICA DE VISIBILIDAD AL VOLVER DE UN SUB-FRAGMENTO
        // ------------------------------------------------------------------
        childFragmentManager.addOnBackStackChangedListener {
            if (childFragmentManager.backStackEntryCount == 0) {
                // Si la pila de sub-Fragments está vacía, mostramos el menú de botones
                buttonMenuContainer.visibility = View.VISIBLE

                // Ocultamos el contenedor de sub-Fragments
                view.findViewById<FragmentContainerView>(SUB_FRAGMENT_CONTAINER_ID)?.visibility = View.GONE
            }
        }
    }

    // FUNCIÓN PARA CARGAR SUB-FRAGMENTS DENTRO DE ESTE FRAGMENTO
    private fun cargarSubFragmento(fragment: Fragment) {
        // AJUSTE DE VISIBILIDAD: Oculta el menú de botones y muestra el contenedor
        buttonMenuContainer.visibility = View.GONE
        view?.findViewById<FragmentContainerView>(SUB_FRAGMENT_CONTAINER_ID)?.visibility = View.VISIBLE

        childFragmentManager.beginTransaction()
            .replace(SUB_FRAGMENT_CONTAINER_ID, fragment)
            .addToBackStack(null) // Permite volver al menú de botones de GestionUsuFragment
            .commit()
        }
}
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

class GestionProdFragment : Fragment() {

    // ID del contenedor donde se cargarán los sub-Fragments (Debe coincidir con el XML)
    private val SUB_FRAGMENT_CONTAINER_ID = R.id.sub_fragment_container_prod

    // Referencia al ConstraintLayout que contiene los botones
    private lateinit var buttonMenuContainer: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestion_prod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inicialización de Vistas
        val btnVerProd: Button = view.findViewById(R.id.btn_ver_prod)
        val btnEditProd: Button = view.findViewById(R.id.btn_edit_prod)
        val btnElimProd: Button = view.findViewById(R.id.btn_elim_prod)
        val btnAddProd: Button = view.findViewById(R.id.btn_add_prod) // 👈 Botón Añadir
        val ivAtras: ImageView = view.findViewById(R.id.imageView13)

        buttonMenuContainer = view.findViewById(R.id.button_menu_prod)

        // ------------------------------------------------------------------
        // LÓGICA DE NAVEGACIÓN
        // ------------------------------------------------------------------

        // Botón VER PRODUCTO
        btnVerProd.setOnClickListener {
            cargarSubFragmento(VerProdFragment())
        }

        // Botón EDITAR PRODUCTO
        btnEditProd.setOnClickListener {
            cargarSubFragmento(EditProdFragment())
        }

        // Botón ELIMINAR PRODUCTO
        btnElimProd.setOnClickListener {
            cargarSubFragmento(ElimProdFragment())
        }

        // 🚨 Botón AÑADIR PRODUCTO
        btnAddProd.setOnClickListener {
            cargarSubFragmento(AddProdFragment())
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
                // Mostrar el menú de botones
                buttonMenuContainer.visibility = View.VISIBLE

                // Ocultar el contenedor de sub-Fragments
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
            .addToBackStack(null) // Permite volver al menú de botones de GestionProdFragment
            .commit()
        }
}
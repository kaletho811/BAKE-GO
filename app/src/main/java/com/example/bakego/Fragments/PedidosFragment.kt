package com.example.bakego.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.bakego.R
import com.example.bakego.Data.PedidosManager
import com.example.bakego.Data.Pedido
import android.widget.ImageView // Importación añadida, aunque no se usa en este fragmento, es común tenerla

class PedidosFragment : Fragment() {

    private lateinit var pedidosContainer: LinearLayout
    private lateinit var scrollPedidosList: ScrollView
    private lateinit var tvNoPedidos: TextView
    private lateinit var backButton: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pedidos, container, false)

        // 1. Enlazar vistas
        scrollPedidosList = view.findViewById(R.id.scroll_pedidos_list)
        pedidosContainer = view.findViewById(R.id.pedidos_container)
        tvNoPedidos = view.findViewById(R.id.tv_no_pedidos)
        backButton = view.findViewById(R.id.ic_back_pedidos)

        // Lógica para volver atrás
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        cargarListaPedidos()

        return view
    }

    override fun onResume() {
        super.onResume()
        cargarListaPedidos()
    }

    private fun cargarListaPedidos() {
        val pedidos = PedidosManager.obtenerPedidos()

        pedidosContainer.removeAllViews()

        if (pedidos.isEmpty()) {
            tvNoPedidos.visibility = View.VISIBLE
            scrollPedidosList.visibility = View.GONE
        } else {
            tvNoPedidos.visibility = View.GONE
            scrollPedidosList.visibility = View.VISIBLE

            pedidos.forEach { pedido ->

                val cardView = CardView(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(0, 0, 0, 16)
                    }
                    radius = 12f
                    cardElevation = 4f
                    setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                }

                val cardLayout = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(32, 32, 32, 32)
                }

                val tvNumero = TextView(requireContext()).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    text = "Pedido #${pedido.numero}"

                    setTextAppearance(android.R.style.TextAppearance_Medium)

                    setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                cardLayout.addView(tvNumero)

                val tvPostres = TextView(requireContext()).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    text = "Postres: ${pedido.postres}"
                    setTextAppearance(android.R.style.TextAppearance_Small)
                    setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                cardLayout.addView(tvPostres)

                val tvDireccion = TextView(requireContext()).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    text = "Dirección: ${pedido.direccion}"
                    setTextAppearance(android.R.style.TextAppearance_Small)
                    setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                cardLayout.addView(tvDireccion)

                cardView.addView(cardLayout)
                pedidosContainer.addView(cardView)
            }
            }
        }
}
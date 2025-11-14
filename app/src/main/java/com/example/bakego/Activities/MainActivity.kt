package com.example.bakego.Activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bakego.Fragments.PerfilFragment
import androidx.fragment.app.FragmentManager
import com.example.bakego.R
import android.widget.FrameLayout
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var icPerfilMain: ImageView
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var contentMainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        icPerfilMain = findViewById(R.id.ic_perfil_main)
        fragmentContainer = findViewById(R.id.fragment_container)
        contentMainLayout = findViewById(R.id.content_main_layout)

        icPerfilMain.setOnClickListener {
            mostrarFragmentoPerfil()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                    icPerfilMain.postDelayed({
                        if (supportFragmentManager.backStackEntryCount == 0) {
                            fragmentContainer.visibility = View.GONE
                            contentMainLayout.visibility = View.VISIBLE
                        }
                    }, 50)

                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun mostrarFragmentoPerfil() {
        contentMainLayout.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        val perfilFragment = PerfilFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, perfilFragment)
            .addToBackStack(null)
            .commit()
    }
}
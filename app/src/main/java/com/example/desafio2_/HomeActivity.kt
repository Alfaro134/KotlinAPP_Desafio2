package com.example.desafio2_

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.desafio2_.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        aplicarInsets()

        val usuario = auth.currentUser
        if (usuario == null) {
            abrirLogin()
            return
        }

        binding.textoCorreo.text = usuario.email
        binding.botonIniciarQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
        binding.botonCerrarSesion.setOnClickListener {
            auth.signOut()
            abrirLogin()
        }
    }

    private fun aplicarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.raiz) { vista, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            vista.updatePadding(top = barras.top, bottom = barras.bottom)
            insets
        }
    }

    private fun abrirLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }
}

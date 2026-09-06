package com.example.desafio2_

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.desafio2_.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        aplicarInsets()

        binding.botonRegistrar.setOnClickListener { registrar() }
        binding.botonIrLogin.setOnClickListener { finish() }
    }

    private fun aplicarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.raiz) { vista, insets ->
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            vista.updatePadding(top = barras.top, bottom = barras.bottom)
            insets
        }
    }

    private fun registrar() {
        val correo = binding.campoCorreo.text?.toString()?.trim().orEmpty()
        val clave = binding.campoClave.text?.toString().orEmpty()
        val confirmar = binding.campoConfirmar.text?.toString().orEmpty()

        binding.contenedorCorreo.error = null
        binding.contenedorClave.error = null
        binding.contenedorConfirmar.error = null

        if (!datosValidos(correo, clave, confirmar)) return

        mostrarCarga(true)
        auth.createUserWithEmailAndPassword(correo, clave)
            .addOnCompleteListener(this) { tarea ->
                mostrarCarga(false)
                if (tarea.isSuccessful) {
                    abrirHome()
                } else {
                    Snackbar.make(binding.raiz, traducirError(tarea.exception), Snackbar.LENGTH_LONG).show()
                }
            }
    }

    private fun datosValidos(correo: String, clave: String, confirmar: String): Boolean {
        var valido = true
        if (correo.isEmpty()) {
            binding.contenedorCorreo.error = getString(R.string.error_correo_requerido)
            valido = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.contenedorCorreo.error = getString(R.string.error_correo_invalido)
            valido = false
        }
        if (clave.isEmpty()) {
            binding.contenedorClave.error = getString(R.string.error_clave_requerida)
            valido = false
        } else if (clave.length < 6) {
            binding.contenedorClave.error = getString(R.string.error_clave_corta)
            valido = false
        }
        if (confirmar.isEmpty()) {
            binding.contenedorConfirmar.error = getString(R.string.error_confirmar_requerida)
            valido = false
        } else if (confirmar != clave) {
            binding.contenedorConfirmar.error = getString(R.string.error_claves_no_coinciden)
            valido = false
        }
        return valido
    }

    private fun traducirError(error: Exception?): String = when (error) {
        is FirebaseAuthUserCollisionException -> getString(R.string.error_correo_en_uso)
        is FirebaseAuthWeakPasswordException -> getString(R.string.error_clave_debil)
        is FirebaseAuthInvalidCredentialsException -> getString(R.string.error_correo_invalido)
        is FirebaseNetworkException -> getString(R.string.error_sin_conexion)
        else -> getString(R.string.error_generico)
    }

    private fun mostrarCarga(activo: Boolean) {
        binding.progreso.visibility = if (activo) View.VISIBLE else View.GONE
        binding.botonRegistrar.isEnabled = !activo
        binding.botonIrLogin.isEnabled = !activo
        binding.campoCorreo.isEnabled = !activo
        binding.campoClave.isEnabled = !activo
        binding.campoConfirmar.isEnabled = !activo
    }

    private fun abrirHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()
    }
}

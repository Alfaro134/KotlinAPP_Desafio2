package com.example.desafio2_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    lateinit var tvTipo: TextView
    lateinit var tvNivel: TextView
    lateinit var tvPuntaje: TextView
    lateinit var tvMensaje: TextView
    lateinit var btnNuevoQuiz: Button
    lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        tvTipo = findViewById(R.id.tvTipo)
        tvNivel = findViewById(R.id.tvNivel)
        tvPuntaje = findViewById(R.id.tvPuntaje)
        tvMensaje = findViewById(R.id.tvMensaje)
        btnNuevoQuiz = findViewById(R.id.btnNuevoQuiz)
        btnRegresar = findViewById(R.id.btnRegresar)

        val tipo = intent.getStringExtra("TIPO").toString()
        val dificultad = intent.getStringExtra("DIFICULTAD").toString()
        val aciertos = intent.getIntExtra("ACIERTOS", 0)
        val total = intent.getIntExtra("TOTAL", 0)

        tvTipo.text = tipo
        tvNivel.text = dificultad
        tvPuntaje.text = getString(R.string.quiz_resultado, aciertos, total)
        tvMensaje.text = obtenerMensaje(aciertos, total, dificultad)

        btnNuevoQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun obtenerMensaje(aciertos: Int, total: Int, dificultad: String): String {
        return when {
            aciertos <= 1 -> getString(R.string.resultado_mensaje_bajo)
            aciertos <= 3 -> getString(R.string.resultado_mensaje_medio)
            aciertos < total -> getString(R.string.resultado_mensaje_alto)
            dificultad == "Dificil" -> getString(R.string.resultado_mensaje_perfecto_dificil)
            else -> getString(R.string.resultado_mensaje_perfecto_facil)
        }
    }
}

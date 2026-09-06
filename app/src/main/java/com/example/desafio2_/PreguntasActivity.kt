package com.example.desafio2_

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PreguntasActivity : AppCompatActivity() {

    lateinit var tvCategoria: TextView
    lateinit var tvNivel: TextView
    lateinit var tvProgreso: TextView
    lateinit var tvEnunciado: TextView
    lateinit var btnOpcion1: Button
    lateinit var btnOpcion2: Button
    lateinit var btnOpcion3: Button
    lateinit var btnRegresar: Button

    lateinit var preguntas: Array<Pregunta>
    var indice: Int = 0
    var aciertos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas)

        tvCategoria = findViewById(R.id.tvCategoria)
        tvNivel = findViewById(R.id.tvNivel)
        tvProgreso = findViewById(R.id.tvProgreso)
        tvEnunciado = findViewById(R.id.tvEnunciado)
        btnOpcion1 = findViewById(R.id.btnOpcion1)
        btnOpcion2 = findViewById(R.id.btnOpcion2)
        btnOpcion3 = findViewById(R.id.btnOpcion3)
        btnRegresar = findViewById(R.id.btnRegresar)

        val tipo = intent.getStringExtra("TIPO").toString()
        val dificultad = intent.getStringExtra("DIFICULTAD").toString()

        preguntas = obtenerPreguntas(tipo, dificultad)
        tvCategoria.text = tipo
        tvNivel.text = "Nivel $dificultad"

        if (savedInstanceState != null) {
            indice = savedInstanceState.getInt("INDICE")
            aciertos = savedInstanceState.getInt("ACIERTOS")
        }

        btnOpcion1.setOnClickListener {
            responder(0)
        }

        btnOpcion2.setOnClickListener {
            responder(1)
        }

        btnOpcion3.setOnClickListener {
            responder(2)
        }

        btnRegresar.setOnClickListener {
            finish()
        }

        mostrarPregunta()
    }

    private fun mostrarPregunta() {
        if (indice >= preguntas.size) {
            mostrarResultado()
            return
        }

        val pregunta = preguntas[indice]

        tvProgreso.text = "Pregunta ${indice + 1} de ${preguntas.size}"
        tvEnunciado.text = pregunta.enunciado
        btnOpcion1.text = pregunta.opciones[0]
        btnOpcion2.text = pregunta.opciones[1]
        btnOpcion3.text = pregunta.opciones[2]
    }

    private fun responder(opcion: Int) {
        if (opcion == preguntas[indice].respuestaCorrecta) {
            aciertos++
        }

        indice++
        mostrarPregunta()
    }

    private fun mostrarResultado() {
        tvProgreso.text = getString(R.string.quiz_finalizado)
        tvEnunciado.text = "Respuestas correctas: $aciertos de ${preguntas.size}"

        btnOpcion1.visibility = View.GONE
        btnOpcion2.visibility = View.GONE
        btnOpcion3.visibility = View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("INDICE", indice)
        outState.putInt("ACIERTOS", aciertos)
    }
}

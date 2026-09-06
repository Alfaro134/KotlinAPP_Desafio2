package com.example.desafio2_

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RevisionActivity : AppCompatActivity() {

    lateinit var contenedorRevision: LinearLayout
    lateinit var btnRegresar: Button

    lateinit var preguntas: Array<Pregunta>
    lateinit var selecciones: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revision)

        contenedorRevision = findViewById(R.id.contenedorRevision)
        btnRegresar = findViewById(R.id.btnRegresar)

        val tipo = intent.getStringExtra("TIPO").toString()
        val dificultad = intent.getStringExtra("DIFICULTAD").toString()

        preguntas = obtenerPreguntas(tipo, dificultad)
        selecciones = intent.getIntArrayExtra("SELECCIONES") ?: IntArray(preguntas.size) { -1 }

        construirRevision()

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun construirRevision() {
        preguntas.forEachIndexed { posicion, pregunta ->
            val item = layoutInflater.inflate(R.layout.item_revision, contenedorRevision, false)
            val tvEnunciado = item.findViewById<TextView>(R.id.tvEnunciado)
            val tvTuRespuesta = item.findViewById<TextView>(R.id.tvTuRespuesta)
            val tvCorrecta = item.findViewById<TextView>(R.id.tvCorrecta)

            val indiceElegido = selecciones.getOrElse(posicion) { -1 }
            val textoElegido = pregunta.opciones.getOrElse(indiceElegido) { getString(R.string.revision_sin_responder) }
            val textoCorrecto = pregunta.opciones[pregunta.respuestaCorrecta]
            val acerto = indiceElegido == pregunta.respuestaCorrecta

            tvEnunciado.text = "${posicion + 1}. ${pregunta.enunciado}"
            tvTuRespuesta.text = getString(R.string.revision_tu_respuesta, textoElegido)
            tvCorrecta.text = getString(R.string.revision_correcta, textoCorrecto)

            val color = if (acerto) R.color.quiz_correcto else R.color.quiz_incorrecto
            tvTuRespuesta.setTextColor(ContextCompat.getColor(this, color))

            contenedorRevision.addView(item)
        }
    }
}

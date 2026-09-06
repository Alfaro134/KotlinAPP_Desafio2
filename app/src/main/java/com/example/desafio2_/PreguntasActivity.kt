package com.example.desafio2_

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PreguntasActivity : AppCompatActivity() {

    lateinit var raiz: ScrollView
    lateinit var tvCategoria: TextView
    lateinit var tvNivel: TextView
    lateinit var contenedorPreguntas: LinearLayout
    lateinit var tvValidacion: TextView
    lateinit var contenedorResultado: LinearLayout
    lateinit var tvTipoRealizado: TextView
    lateinit var tvResultado: TextView
    lateinit var btnFinalizar: Button
    lateinit var btnReiniciar: Button
    lateinit var btnRegresar: Button

    lateinit var tipoQuiz: String
    lateinit var preguntas: Array<Pregunta>
    val grupos: MutableList<RadioGroup> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas)

        raiz = findViewById(R.id.raiz)
        tvCategoria = findViewById(R.id.tvCategoria)
        tvNivel = findViewById(R.id.tvNivel)
        contenedorPreguntas = findViewById(R.id.contenedorPreguntas)
        tvValidacion = findViewById(R.id.tvValidacion)
        contenedorResultado = findViewById(R.id.contenedorResultado)
        tvTipoRealizado = findViewById(R.id.tvTipoRealizado)
        tvResultado = findViewById(R.id.tvResultado)
        btnFinalizar = findViewById(R.id.btnFinalizar)
        btnReiniciar = findViewById(R.id.btnReiniciar)
        btnRegresar = findViewById(R.id.btnRegresar)

        tipoQuiz = intent.getStringExtra("TIPO").toString()
        val dificultad = intent.getStringExtra("DIFICULTAD").toString()

        preguntas = obtenerPreguntas(tipoQuiz, dificultad)
        tvCategoria.text = tipoQuiz
        tvNivel.text = "Nivel $dificultad"

        construirPreguntas()

        btnFinalizar.setOnClickListener {
            finalizar()
        }

        btnReiniciar.setOnClickListener {
            reiniciar()
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun construirPreguntas() {
        val margenOpcion = (6 * resources.displayMetrics.density).toInt()

        preguntas.forEachIndexed { posicion, pregunta ->
            val item = layoutInflater.inflate(R.layout.item_pregunta, contenedorPreguntas, false)
            val tvEnunciado = item.findViewById<TextView>(R.id.tvEnunciado)
            val grupo = item.findViewById<RadioGroup>(R.id.grupoOpciones)

            tvEnunciado.text = "${posicion + 1}. ${pregunta.enunciado}"

            pregunta.opciones.forEach { textoOpcion ->
                val opcion = RadioButton(this)
                opcion.text = textoOpcion
                opcion.textSize = 16f
                opcion.setTextColor(ContextCompat.getColor(this, R.color.quiz_texto))
                val parametros = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
                )
                parametros.topMargin = margenOpcion
                parametros.bottomMargin = margenOpcion
                opcion.layoutParams = parametros
                grupo.addView(opcion)
            }

            contenedorPreguntas.addView(item)
            grupos.add(grupo)
        }
    }

    private fun finalizar() {
        val faltantes = mutableListOf<Int>()
        grupos.forEachIndexed { posicion, grupo ->
            if (grupo.checkedRadioButtonId == -1) {
                faltantes.add(posicion + 1)
            }
        }

        if (faltantes.isNotEmpty()) {
            contenedorResultado.visibility = View.GONE
            tvValidacion.text = getString(R.string.quiz_faltan, faltantes.joinToString(", "))
            tvValidacion.visibility = View.VISIBLE
            return
        }

        var aciertos = 0
        grupos.forEachIndexed { posicion, grupo ->
            val opcionMarcada = grupo.findViewById<RadioButton>(grupo.checkedRadioButtonId)
            val indiceSeleccionado = grupo.indexOfChild(opcionMarcada)
            if (indiceSeleccionado == preguntas[posicion].respuestaCorrecta) {
                aciertos++
            }
        }

        tvValidacion.visibility = View.GONE
        tvTipoRealizado.text = getString(R.string.quiz_tipo_realizado, tipoQuiz)
        tvResultado.text = getString(R.string.quiz_resultado, aciertos, preguntas.size)
        contenedorResultado.visibility = View.VISIBLE
    }

    private fun reiniciar() {
        grupos.forEach { it.clearCheck() }
        tvValidacion.visibility = View.GONE
        contenedorResultado.visibility = View.GONE
        raiz.smoothScrollTo(0, 0)
    }
}

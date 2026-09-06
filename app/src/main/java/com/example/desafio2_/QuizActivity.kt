package com.example.desafio2_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    lateinit var grupoDificultad: RadioGroup
    lateinit var btnCulturaGeneral: Button
    lateinit var btnCiencia: Button
    lateinit var btnDeportes: Button
    lateinit var btnHistoria: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        grupoDificultad = findViewById(R.id.grupoDificultad)
        btnCulturaGeneral = findViewById(R.id.btnCulturaGeneral)
        btnCiencia = findViewById(R.id.btnCiencia)
        btnDeportes = findViewById(R.id.btnDeportes)
        btnHistoria = findViewById(R.id.btnHistoria)

        btnCulturaGeneral.setOnClickListener {
            abrirPreguntas("Cultura general")
        }

        btnCiencia.setOnClickListener {
            abrirPreguntas("Ciencia")
        }

        btnDeportes.setOnClickListener {
            abrirPreguntas("Deportes")
        }

        btnHistoria.setOnClickListener {
            abrirPreguntas("Historia")
        }
    }

    private fun dificultadSeleccionada(): String {
        return if (grupoDificultad.checkedRadioButtonId == R.id.radioFacil) {
            "Facil"
        } else {
            "Dificil"
        }
    }

    private fun abrirPreguntas(tipo: String) {
        val intent = Intent(this, PreguntasActivity::class.java)
        intent.putExtra("TIPO", tipo)
        intent.putExtra("DIFICULTAD", dificultadSeleccionada())
        startActivity(intent)
    }
}

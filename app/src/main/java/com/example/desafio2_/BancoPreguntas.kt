package com.example.desafio2_

class Pregunta(
    val enunciado: String,
    val opciones: Array<String>,
    val respuestaCorrecta: Int
)

fun obtenerPreguntas(tipo: String, dificultad: String): Array<Pregunta> {
    val esFacil = dificultad == "Facil"

    return when (tipo) {
        "Cultura general" -> if (esFacil) culturaFacil() else culturaDificil()
        "Ciencia" -> if (esFacil) cienciaFacil() else cienciaDificil()
        "Deportes" -> if (esFacil) deportesFacil() else deportesDificil()
        else -> if (esFacil) historiaFacil() else historiaDificil()
    }
}

private fun culturaFacil(): Array<Pregunta> {
    return arrayOf(
        Pregunta(
            "Cuantos dias tiene un ano bisiesto?",
            arrayOf("364", "365", "366"),
            2
        ),
        Pregunta(
            "Que color se obtiene al mezclar azul y amarillo?",
            arrayOf("Verde", "Naranja", "Morado"),
            0
        ),
        Pregunta(
            "En que continente se encuentra Egipto?",
            arrayOf("Asia", "Africa", "Europa"),
            1
        ),
        Pregunta(
            "Cual es el idioma oficial de Brasil?",
            arrayOf("Espanol", "Ingles", "Portugues"),
            2
        ),
        Pregunta(
            "Cuantos lados tiene un triangulo?",
            arrayOf("Tres", "Cuatro", "Cinco"),
            0
        )
    )
}

private fun culturaDificil(): Array<Pregunta> {
    return arrayOf(
        Pregunta(
            "En que ano cayo el Muro de Berlin?",
            arrayOf("1975", "1989", "1991"),
            1
        ),
        Pregunta(
            "Cual es la capital de Australia?",
            arrayOf("Sidney", "Melbourne", "Canberra"),
            2
        ),
        Pregunta(
            "Quien escribio la novela Cien anos de soledad?",
            arrayOf("Gabriel Garcia Marquez", "Mario Vargas Llosa", "Julio Cortazar"),
            0
        ),
        Pregunta(
            "Cual es la moneda oficial de Japon?",
            arrayOf("Won", "Yen", "Yuan"),
            1
        ),
        Pregunta(
            "Cual es el rio mas largo de Africa?",
            arrayOf("Congo", "Niger", "Nilo"),
            2
        )
    )
}

private fun cienciaFacil(): Array<Pregunta> {
    return arrayOf(
        Pregunta(
            "Cual es la formula quimica del agua?",
            arrayOf("CO2", "H2O", "O2"),
            1
        ),
        Pregunta(
            "Que planeta es conocido como el planeta rojo?",
            arrayOf("Marte", "Venus", "Jupiter"),
            0
        ),
        Pregunta(
            "Que organo bombea la sangre en el cuerpo humano?",
            arrayOf("El pulmon", "El higado", "El corazon"),
            2
        ),
        Pregunta(
            "Que gas necesitan las personas para respirar?",
            arrayOf("Nitrogeno", "Oxigeno", "Helio"),
            1
        ),
        Pregunta(
            "A que temperatura hierve el agua a nivel del mar?",
            arrayOf("50 grados", "100 grados", "150 grados"),
            1
        )
    )
}

private fun cienciaDificil(): Array<Pregunta> {
    return arrayOf(
        Pregunta(
            "Cual es el numero atomico del carbono?",
            arrayOf("6", "8", "12"),
            0
        ),
        Pregunta(
            "Que cientifico propuso la teoria de la relatividad?",
            arrayOf("Isaac Newton", "Niels Bohr", "Albert Einstein"),
            2
        ),
        Pregunta(
            "Cual es la unidad de medida de la resistencia electrica?",
            arrayOf("Voltio", "Ohmio", "Amperio"),
            1
        ),
        Pregunta(
            "Que tipo de celula no posee nucleo definido?",
            arrayOf("Procariota", "Eucariota", "Vegetal"),
            0
        ),
        Pregunta(
            "Cual metal se encuentra liquido a temperatura ambiente?",
            arrayOf("Plomo", "Aluminio", "Mercurio"),
            2
        )
    )
}

private fun deportesFacil(): Array<Pregunta> {
    return arrayOf(
        Pregunta(
            "Cuantos jugadores tiene un equipo de futbol en la cancha?",
            arrayOf("Nueve", "Once", "Siete"),
            1
        ),
        Pregunta(
            "En que deporte se utiliza una raqueta y una pelota amarilla?",
            arrayOf("Tenis", "Beisbol", "Boxeo"),
            0
        ),
        Pregunta(
            "Cada cuantos anos se celebran los Juegos Olimpicos?",
            arrayOf("Dos anos", "Seis anos", "Cuatro anos"),
            2
        ),
        Pregunta(
            "Cuantos puntos vale un triple en baloncesto?",
            arrayOf("Uno", "Dos", "Tres"),
            2
        ),
        Pregunta(
            "Que deporte practica Lionel Messi?",
            arrayOf("Natacion", "Futbol", "Beisbol"),
            1
        )
    )
}

private fun deportesDificil(): Array<Pregunta> {
    return arrayOf(
        Pregunta(
            "Que pais gano la Copa Mundial de futbol del ano 2014?",
            arrayOf("Argentina", "Brasil", "Alemania"),
            2
        ),
        Pregunta(
            "Cuantos anillos tiene el simbolo olimpico?",
            arrayOf("Cinco", "Cuatro", "Seis"),
            0
        ),
        Pregunta(
            "En que ciudad se celebraron los Juegos Olimpicos del ano 2016?",
            arrayOf("Londres", "Rio de Janeiro", "Tokio"),
            1
        ),
        Pregunta(
            "Cuanto dura un cuarto de un partido de baloncesto de la NBA?",
            arrayOf("Diez minutos", "Quince minutos", "Doce minutos"),
            2
        ),
        Pregunta(
            "Que jugador de futbol tiene mas Balones de Oro?",
            arrayOf("Lionel Messi", "Cristiano Ronaldo", "Michel Platini"),
            0
        )
    )
}

private fun historiaFacil(): Array<Pregunta> {
    return arrayOf(
        Pregunta(
            "Quien llego a America en el ano 1492?",
            arrayOf("Hernan Cortes", "Cristobal Colon", "Americo Vespucio"),
            1
        ),
        Pregunta(
            "Que civilizacion construyo las piramides de Giza?",
            arrayOf("La egipcia", "La romana", "La maya"),
            0
        ),
        Pregunta(
            "Quien fue el primer presidente de Estados Unidos?",
            arrayOf("Abraham Lincoln", "Thomas Jefferson", "George Washington"),
            2
        ),
        Pregunta(
            "En que ano se independizo El Salvador?",
            arrayOf("1810", "1821", "1830"),
            1
        ),
        Pregunta(
            "En que continente nacio la civilizacion egipcia?",
            arrayOf("Asia", "Europa", "Africa"),
            2
        )
    )
}

private fun historiaDificil(): Array<Pregunta> {
    return arrayOf(
        Pregunta(
            "En que ano inicio la Segunda Guerra Mundial?",
            arrayOf("1914", "1939", "1945"),
            1
        ),
        Pregunta(
            "Quien fue el primer emperador de Roma?",
            arrayOf("Augusto", "Julio Cesar", "Neron"),
            0
        ),
        Pregunta(
            "En que ano llego el ser humano a la Luna por primera vez?",
            arrayOf("1959", "1972", "1969"),
            2
        ),
        Pregunta(
            "Que tratado puso fin a la Primera Guerra Mundial?",
            arrayOf("El Tratado de Paris", "El Tratado de Versalles", "El Tratado de Viena"),
            1
        ),
        Pregunta(
            "Quien lidero el movimiento de independencia de la India?",
            arrayOf("Mahatma Gandhi", "Jawaharlal Nehru", "Bhimrao Ambedkar"),
            0
        )
    )
}

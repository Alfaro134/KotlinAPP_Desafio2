package com.example.desafio2_

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val haySesion = FirebaseAuth.getInstance().currentUser != null
        val destino = if (haySesion) HomeActivity::class.java else LoginActivity::class.java

        startActivity(Intent(this, destino))
        finish()
    }
}

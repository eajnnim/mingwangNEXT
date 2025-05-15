package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
<<<<<<< HEAD
=======

>>>>>>> 08d91d3f5b4e722f186eb6e13724fb1c995882a2
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val button = findViewById<ImageView>(R.id.lets_throw)
<<<<<<< HEAD
=======

>>>>>>> 08d91d3f5b4e722f186eb6e13724fb1c995882a2
        button.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)

        }
    }
}

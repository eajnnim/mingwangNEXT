package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val button = findViewById<ImageView>(R.id.lets_throw)

        button.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)

        }
    }
}

package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
<<<<<<< HEAD
import android.widget.ImageView
=======
>>>>>>> 0538b1d09e7771638e6f122e90f2ee85bee23707
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

<<<<<<< HEAD
        val button = findViewById<ImageView>(R.id.lets_throw)
=======
        val button = findViewById<Button>(R.id.startMapButton)
>>>>>>> 0538b1d09e7771638e6f122e90f2ee85bee23707
        button.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)

        }
    }
}

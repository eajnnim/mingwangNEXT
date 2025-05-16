package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class FailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fail) // ✅ 고침

        val label = intent.getStringExtra("label") ?: "unknown"
        val location = intent.getStringExtra("location") ?: "unknown"
        val timeMillis = intent.getLongExtra("time", 0L)
        val coin = intent.getIntExtra("coin", 0)
        val exp = intent.getIntExtra("exp", 0)

        val timeString = SimpleDateFormat("yyyy.MM.dd HH:mm a", Locale.getDefault()).format(Date(timeMillis))

        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = """
            🕒 $timeString
            📍 $location
            인식불가
        """.trimIndent()

        findViewById<ImageView>(R.id.throw_fail).postDelayed({
            startActivity(Intent(this, MapsActivity::class.java))
            finish()
        }, 2000)
    }
}

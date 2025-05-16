package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        // SharedPreferences에서 저장된 데이터 불러오기
        val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
        val label = prefs.getString("last_label", "unknown") ?: "unknown"
        val location = prefs.getString("last_location", "unknown") ?: "unknown"
        val timeMillis = prefs.getLong("last_time", 0L)
        val coin = prefs.getInt("last_coin", 0)
        val exp = prefs.getInt("last_exp", 0)

        val timeString = SimpleDateFormat("yyyy.MM.dd hh:mm a", Locale.getDefault())
            .format(Date(timeMillis))

        // 텍스트뷰에 표시
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = """
            🕒 $timeString
            📍 $location
            ✅ $label
            💰 +$coin / EXP +$exp
        """.trimIndent()

        // 2초 후 지도 화면으로 이동
        findViewById<ImageView>(R.id.throw_success).postDelayed({
            startActivity(Intent(this, MapsActivity::class.java))
            finish()
        }, 2000)
    }
}

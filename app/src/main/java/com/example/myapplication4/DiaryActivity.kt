package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DiaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val backButtonArea = findViewById<View>(R.id.backButtonArea6)

        backButtonArea.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)

            finish() // 현재 액티비티 종료 = 뒤로가기
        }

        val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
        val label = prefs.getString("last_label", "unknown") ?: "unknown"
        val location = prefs.getString("last_location", "unknown") ?: "unknown"
        val timeMillis = prefs.getLong("last_time", 0L)
        val coin = prefs.getInt("last_coin", 0)
        val exp = prefs.getInt("last_exp", 0)

        val timeString = SimpleDateFormat("yyyy.MM.dd hh:mm a", Locale.getDefault())
            .format(Date(timeMillis))

        val rvDiary = findViewById<RecyclerView>(R.id.rvDiary)
        rvDiary.layoutManager = LinearLayoutManager(this)
        val data = listOf(
            DiaryEntry(timeString, location, label, coin, exp)
            )
        rvDiary.adapter = DiaryAdapter(data)
    }
}

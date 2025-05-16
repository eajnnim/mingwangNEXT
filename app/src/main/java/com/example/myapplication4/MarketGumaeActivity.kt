package com.example.myapplication4

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MarketGumaeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_gumae)  // ← XML 레이아웃 파일명 정확히 맞춰줘
        val backButtonArea = findViewById<View>(R.id.backButtonArea)

        backButtonArea.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료 = 뒤로가기
        }
        val imageView = findViewById<ImageView>(R.id.menuImage)
        val nameTextView = findViewById<TextView>(R.id.menuName)
        val tagTextView = findViewById<TextView>(R.id.menuTag)
        val priceTextView = findViewById<TextView>(R.id.menuPrice)

        // 인텐트에서 데이터 꺼내기
        val imageResId = intent.getIntExtra("imageResId", 0)
        val imageName = intent.getStringExtra("imageName") ?: ""
        val menuName = intent.getStringExtra("menuName") ?: ""
        val menuTag = intent.getStringExtra("menuTag") ?: ""
        val menuPrice = intent.getStringExtra("menuPrice") ?: ""

        imageView.setImageResource(imageResId)
        nameTextView.text = menuName
        tagTextView.text = menuTag
        priceTextView.text = menuPrice

        val backButton = findViewById<View>(R.id.market_gumae_dwi)
        backButton.setOnClickListener {
            val intent = Intent(this, MarketListActivity::class.java)
            startActivity(intent)
            finish()
        }

        val confirmButton = findViewById<View>(R.id.market_gumae_hwak)
        confirmButton.setOnClickListener {

            // 현재 시간
            val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())

            // SharedPreferences에 저장
            val sharedPref = getSharedPreferences("diary", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()

            val historySize = sharedPref.getInt("history_size", 0)
            editor.putString("history_$historySize", "$imageName|$currentTime")
            editor.putInt("history_size", historySize + 1)
            editor.apply()


            val intent = Intent(this, MarketGumaewanActivity::class.java)

            // 현재 Activity에서 받은 정보 다시 넘겨주기
            intent.putExtra("imageResId", imageResId)
            intent.putExtra("menuName", menuName)
            intent.putExtra("menuTag", menuTag)
            intent.putExtra("menuPrice", menuPrice)

            startActivity(intent)
        }

    }
}
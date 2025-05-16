package com.example.myapplication_market

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MarketDiaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_diary)  // XML 파일 이름 맞게

        // 🔹 왼쪽 절반 클릭 시 market_list 화면으로 이동
        val leftHalf = findViewById<View>(R.id.left_half)
        leftHalf.setOnClickListener {
            val intent = Intent(this, MarketListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

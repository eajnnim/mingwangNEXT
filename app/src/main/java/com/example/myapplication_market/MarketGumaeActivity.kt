package com.example.myapplication_market

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


class MarketGumaeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_gumae)  // ← XML 레이아웃 파일명 정확히 맞춰줘

        val imageView = findViewById<ImageView>(R.id.menuImage)
        val nameTextView = findViewById<TextView>(R.id.menuName)
        val tagTextView = findViewById<TextView>(R.id.menuTag)
        val priceTextView = findViewById<TextView>(R.id.menuPrice)

        // 인텐트에서 데이터 꺼내기
        val imageResId = intent.getIntExtra("imageResId", 0)
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
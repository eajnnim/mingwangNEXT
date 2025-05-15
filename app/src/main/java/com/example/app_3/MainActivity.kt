package com.example.app_3          // ← 자기 패키지 그대로

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge        // Material3 의 시스템‑바 처리
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_3.databinding.MarketCardBinding
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1) 시스템 바 투명 처리(선택)
        enableEdgeToEdge()

        // 2) 레이아웃 한 번만 지정!  (※ 중복 setContentView 제거)
        setContentView(R.layout.activity_main)

        // 3) (선택) 인셋 맞춰 패딩
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left , bars.top, bars.right, bars.bottom)
            insets
        }

        // 4) ConstraintLayout 찾아서 클릭 리스너 달기
        val mypageButton = findViewById<ConstraintLayout>(R.id.mypage_card)

        mypageButton.setOnClickListener {
            // MypageActivity 로 화면 전환
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }

        // 4) MaterialCardView 찾아서 클릭 리스너 달기
        val marketButton = findViewById<MaterialCardView>(R.id.market_card)

        marketButton.setOnClickListener {
            // MarketActivity 로 화면 전환
            val intent = Intent(this, MarketActivity::class.java)
            startActivity(intent)
        }

        // 4) CardView 찾아서 클릭 리스너 달기
        val diaryButton = findViewById<CardView>(R.id.diary_card)

        diaryButton.setOnClickListener {
            // DiaryActivity 로 화면 전환
            val intent = Intent(this, DiaryActivity::class.java)
            startActivity(intent)
        }
    }
}

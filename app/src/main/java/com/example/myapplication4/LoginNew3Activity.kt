package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginNew3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new3)
        val backButtonArea = findViewById<View>(R.id.backButtonArea)

        backButtonArea.setOnClickListener {
            val intent = Intent(this, LoginBackgroundActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료 = 뒤로가기
        }

        val backButtonArea2 = findViewById<View>(R.id.previousButton)
        backButtonArea2.setOnClickListener {
            val intent = Intent(this, LoginNewActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료 = 뒤로가기

        }
        val backButtonArea3 = findViewById<View>(R.id.nextButton)

        backButtonArea3.setOnClickListener {
            val intent = Intent(this, LoginNew4Activity::class.java)
            startActivity(intent)

        }

    }
}
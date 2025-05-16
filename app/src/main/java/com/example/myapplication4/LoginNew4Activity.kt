package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginNew4Activity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new4)
        val backButtonArea3 = findViewById<View>(R.id.previousButton)
        backButtonArea3.setOnClickListener {
            val intent = Intent(this, LoginNew3Activity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료 = 뒤로가기

        }
        val backButtonArea5 = findViewById<View>(R.id.nextButton)
        backButtonArea5.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)

        }

    }
}
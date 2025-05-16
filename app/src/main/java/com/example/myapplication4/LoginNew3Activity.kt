package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginNew3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new3)
        val backButtonArea2 = findViewById<View>(R.id.previousButton)
        backButtonArea2.setOnClickListener {
            val intent = Intent(this, LoginNewActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료 = 뒤로가기

        }
        val backButtonArea3 = findViewById<View>(R.id.nextButton)

        backButtonArea3.setOnClickListener {
<<<<<<< HEAD
            val intent = Intent(this, IntroActivity::class.java)
=======
            val intent = Intent(this, LoginNew4Activity::class.java)
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
            startActivity(intent)

        }

    }
}
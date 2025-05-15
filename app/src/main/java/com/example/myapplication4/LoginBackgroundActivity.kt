package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class LoginBackgroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_background)  // 너가 만든 XML 연결

        // 이미지 클릭 시 로그인 창으로 이동
        val loginSecond = findViewById<ImageView>(R.id.loginSecond)
        val loginThird = findViewById<ImageView>(R.id.loginThird)

        loginThird.setOnClickListener {
            val intent = Intent(this, LoginNewActivity::class.java)
            startActivity(intent)
        }

        loginSecond.setOnClickListener {
            val intent = Intent(this, LoginPastActivity::class.java)
            startActivity(intent)
            finish()  // 이 화면은 종료하고 LoginActivity로 이동
        }
        }
    }


package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginNew2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new2) // 레이아웃도 반드시 존재해야 함
        val backButtonArea = findViewById<View>(R.id.backButtonArea)

        backButtonArea.setOnClickListener {
            val intent = Intent(this, LoginNew3Activity::class.java)
            startActivity(intent)

            finish() // 현재 액티비티 종료 = 뒤로가기
        }

    }
}

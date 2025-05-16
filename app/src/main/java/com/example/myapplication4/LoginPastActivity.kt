package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class LoginPastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_past)

        val emailImg = findViewById<ImageView>(R.id.image1)
        val pwImg = findViewById<ImageView>(R.id.image2)
        val emailEdit = findViewById<EditText>(R.id.emailEdit)
        val pwEdit = findViewById<EditText>(R.id.passwordEdit)
        val loginButton = findViewById<Button>(R.id.loginButton)

        emailImg.setOnClickListener {
            emailEdit.visibility = View.VISIBLE
            emailEdit.requestFocus()
        }

        pwImg.setOnClickListener {
            pwEdit.visibility = View.VISIBLE
            pwEdit.requestFocus()
        }
        val backButtonArea = findViewById<View>(R.id.backButtonArea)

        backButtonArea.setOnClickListener {
            val intent = Intent(this, LoginBackgroundActivity::class.java)
            startActivity(intent)

            finish() // 현재 액티비티 종료 = 뒤로가기
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}

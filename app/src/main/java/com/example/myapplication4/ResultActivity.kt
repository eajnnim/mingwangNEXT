package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val result = intent.getBooleanExtra("result", false)

        if (result) {
            startActivity(Intent(this, SuccessActivity::class.java))
        } else {
            startActivity(Intent(this, FailActivity::class.java))
        }

        finish()
    }
}

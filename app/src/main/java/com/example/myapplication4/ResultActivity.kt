// 📄 ResultActivity.kt — 분석 결과에 따라 UI 분기 후 지도로 복귀

package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result) // ← 이거 꼭 있어야 해!

        val result = intent.getBooleanExtra("result", false)
        // 결과를 TextView 등에 표시


        if (result) {
            startActivity(Intent(this, SuccessActivity::class.java))
        } else {
            startActivity(Intent(this, FailActivity::class.java))
        }

            finish()
    }
}

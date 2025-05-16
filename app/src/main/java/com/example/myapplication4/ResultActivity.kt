// 📄 ResultActivity.kt — 분석 결과에 따라 UI 분기 후 지도로 복귀

package com.example.myapplication4

<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
=======
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result) // ← 이거 꼭 있어야 해!

        val result = intent.getBooleanExtra("result", false)
        // 결과를 TextView 등에 표시

<<<<<<< HEAD

        if (result) {
            startActivity(Intent(this, SuccessActivity::class.java))
        } else {
            startActivity(Intent(this, FailActivity::class.java))
        }

            finish()
    }
}
=======
        val resultText = findViewById<TextView>(R.id.resultText)
        val resultImage = findViewById<ImageView>(R.id.resultImage)

        if (result) {
            resultText.text = "✔ 해결되었습니다!\n코인이 적립되었습니다!"
        } else {
            resultText.text = "❌ 인증되지 못했습니다\n다시 시도해주세요."
        }

        // 일정 시간 후 자동 종료 (예: 2초)
        resultImage.postDelayed({
            finish()
        }, 2000)
    }
} 
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17

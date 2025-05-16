package com.example.myapplication4
import android.widget.ImageView
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new) // 연결한 layout은 이건데
        val backButtonArea = findViewById<View>(R.id.backButtonArea)

        backButtonArea.setOnClickListener {
            val intent = Intent(this, LoginBackgroundActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료 = 뒤로가기
        }
        val image4 = findViewById<ImageView>(R.id.image4)

        image4.setOnClickListener {
            val intent = Intent(this, LoginNew2Activity::class.java)
            startActivity(intent)
            finish()}


    }
}

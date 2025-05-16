package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        val button = findViewById<ImageView>(R.id.lets_throw)
        button.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            button.setOnClickListener {
                val bounce = ScaleAnimation(
                    1f, 1.2f, // X: from 1x to 1.2x
                    1f, 1.2f, // Y: from 1x to 1.2x
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f, // 중심축 X
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f  // 중심축 Y
                ).apply {
                    duration = 400
                    interpolator = BounceInterpolator()
                    fillAfter = true // 애니메이션 후 상태 유지
                }
                button.startAnimation(bounce)
            }
            startActivity(intent)
        }

        val marketButton = findViewById<ImageView>(R.id.green_market)
        marketButton.setOnClickListener {
            val intent2 = Intent(this, MarketListActivity::class.java)
            marketButton.setOnClickListener {
                val bounce = ScaleAnimation(
                    1f, 1.2f, // X: from 1x to 1.2x
                    1f, 1.2f, // Y: from 1x to 1.2x
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f, // 중심축 X
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f  // 중심축 Y
                ).apply {
                    duration = 400
                    interpolator = BounceInterpolator()
                    fillAfter = true // 애니메이션 후 상태 유지
                }
                marketButton.startAnimation(bounce)
            }
            startActivity(intent2)
        }

        val diaryButton = findViewById<ImageView>(R.id.diary)
        diaryButton.setOnClickListener {
            val intent3 = Intent(this, DiaryActivity::class.java)
            diaryButton.setOnClickListener {
                val bounce = ScaleAnimation(
                    1f, 1.2f, // X: from 1x to 1.2x
                    1f, 1.2f, // Y: from 1x to 1.2x
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f, // 중심축 X
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f  // 중심축 Y
                ).apply {
                    duration = 400
                    interpolator = BounceInterpolator()
                    fillAfter = true // 애니메이션 후 상태 유지
                }
                diaryButton.startAnimation(bounce)
            }
            startActivity(intent3)
        }
    }
}

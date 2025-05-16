package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity

class LoginBackgroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_background)  // 너가 만든 XML 연결

        // 이미지 클릭 시 로그인 창으로 이동
        val loginSecond = findViewById<ImageView>(R.id.loginSecond)
        val loginThird = findViewById<ImageView>(R.id.loginThird)

        loginThird.setOnClickListener {
<<<<<<< HEAD
=======
            val intent = Intent(this, LoginNewActivity::class.java)
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
            loginThird.setOnClickListener {
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
                loginThird.startAnimation(bounce)
            }
<<<<<<< HEAD
            val intent = Intent(this, LoginNewActivity::class.java)
=======
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
            startActivity(intent)
        }

        loginSecond.setOnClickListener {
<<<<<<< HEAD
=======
            val intent = Intent(this, LoginPastActivity::class.java)
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
            loginSecond.setOnClickListener {
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
<<<<<<< HEAD

                loginSecond.startAnimation(bounce)
            }
            val intent = Intent(this, LoginPastActivity::class.java)
=======
                loginSecond.startAnimation(bounce)
            }
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
            startActivity(intent)
            finish()  // 이 화면은 종료하고 LoginActivity로 이동
        }
        }
    }


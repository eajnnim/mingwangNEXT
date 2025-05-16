package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class LoginPastActivity : AppCompatActivity() {

    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var mDatabaseRef: DatabaseReference
    private lateinit var mEtEmail: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mBtnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_past)

        mFirebaseAuth = FirebaseAuth.getInstance()
        mDatabaseRef = FirebaseDatabase
            .getInstance()
            .getReference("users")

        mEtEmail = findViewById(R.id.et_email)
        mEtPassword = findViewById(R.id.et_password)
        mBtnLogin = findViewById(R.id.btn_login)

        mBtnLogin.setOnClickListener {
            val strEmail    = mEtEmail.text.toString().trim()
            val strPassword = mEtPassword.text.toString().trim()

            if (strEmail.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (strPassword.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mFirebaseAuth
                .signInWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // 로그인 성공
                        val intent = Intent(this, IntroActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // 로그인 실패
                        val exception = task.exception
                        val errorMessage = if (exception is FirebaseAuthException) {
                            when (exception.errorCode) {
                                "ERROR_USER_NOT_FOUND" -> "존재하지 않는 계정입니다."
                                "ERROR_WRONG_PASSWORD" -> "비밀번호가 일치하지 않습니다."
                                "ERROR_INVALID_EMAIL" -> "올바르지 않은 이메일 형식입니다."
                                "ERROR_INVALID_CREDENTIAL" -> {
                                    if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
                                        "올바르지 않은 이메일 형식입니다."
                                    } else {
                                        "이메일 또는 비밀번호를 확인해주세요."
                                    }
                                }
                                else -> {
                                    // 기타 FirebaseAuthException 오류
                                    "로그인에 실패했습니다 (${exception.errorCode}). 잠시 후 다시 시도해주세요."
                                }
                            }
                        } else {
                            // FirebaseAuthException이 아닌 다른 유형의 예외
                            "로그인 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요."
                        }
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
        }

    }
}

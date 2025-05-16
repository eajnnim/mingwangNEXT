package com.example.myapplication4
<<<<<<< HEAD

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseregister.UserAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LoginNewActivity : AppCompatActivity() {

    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var mDatabaseRef: DatabaseReference
    // UI 요소들
    private lateinit var formContainer: LinearLayout
    private lateinit var etEmail: EditText
    private lateinit var etNickname: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPasswordConfirm: EditText
    private lateinit var btnRequestRegister:ImageView

    private lateinit var layoutEmailVerification: LinearLayout
    private lateinit var tvVerificationMessage: TextView
    private lateinit var btnCheckVerification: Button

    private lateinit var backButtonArea: ImageView           // 뒤로가기 버튼

    private var currentFirebaseUser: FirebaseUser? = null
    private var currentNicknameForDb: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_new)

        mFirebaseAuth = FirebaseAuth.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().reference.child("users")

        // UI 요소 초기화
        formContainer = findViewById(R.id.formContainer)
        etEmail = findViewById(R.id.et_email)
        etNickname = findViewById(R.id.et_nickname)
        etPassword = findViewById(R.id.et_password)
        etPasswordConfirm = findViewById(R.id.et_password_confirm)
        btnRequestRegister = findViewById(R.id.image4)

        layoutEmailVerification = findViewById(R.id.layout_email_verification)
        tvVerificationMessage = findViewById(R.id.tv_verification_message)
        btnCheckVerification = findViewById(R.id.btn_check_verification)

        backButtonArea = findViewById(R.id.backButtonArea)

        btnRequestRegister.setOnClickListener {
            handleRegistrationRequest()
        }

        btnCheckVerification.setOnClickListener {
            checkEmailVerificationStatus()
        }

        backButtonArea.setOnClickListener {
            finish() // 현재 액티비티 종료
        }
    }

    private fun handleRegistrationRequest() {
        val strEmail = etEmail.text.toString().trim()
        val strNickname = etNickname.text.toString().trim()
        val strPassword = etPassword.text.toString().trim()
        val strPasswordConfirm = etPasswordConfirm.text.toString().trim()

        if (!validateInput(strEmail, strNickname, strPassword, strPasswordConfirm)) {
            return
        }

        currentNicknameForDb = strNickname

        setFormEnabled(false)

        mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    currentFirebaseUser = mFirebaseAuth.currentUser

                    currentFirebaseUser?.sendEmailVerification()
                        ?.addOnCompleteListener { sendTask ->
                            if (sendTask.isSuccessful) {
                                Toast.makeText(this, "확인 이메일을 발송했습니다. 메일을 확인해주세요.", Toast.LENGTH_LONG).show()
                                formContainer.visibility = View.GONE
                                layoutEmailVerification.visibility = View.VISIBLE
                            } else {
                                Toast.makeText(this, "확인 이메일 발송에 실패했습니다: ${sendTask.exception?.message}", Toast.LENGTH_LONG).show()
                                setFormEnabled(true)
                            }
                        } ?: run {
                        Toast.makeText(this, "회원가입 처리 중 오류 (사용자 정보 없음).", Toast.LENGTH_LONG).show()
                        setFormEnabled(true)
                    }
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthUserCollisionException -> "이미 가입된 이메일입니다."
                        else -> "회원가입에 실패하였습니다: ${task.exception?.message}"
                    }
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                    setFormEnabled(true)
                }
            }
    }

    private fun checkEmailVerificationStatus() {
        btnCheckVerification.isEnabled = false
        tvVerificationMessage.text = "이메일 인증 상태를 확인 중입니다..."

        currentFirebaseUser?.reload()?.addOnCompleteListener { reloadTask ->
            if (reloadTask.isSuccessful) {
                val refreshedUser = mFirebaseAuth.currentUser
                if (refreshedUser != null && refreshedUser.isEmailVerified) {
                    Toast.makeText(this, "이메일 인증이 확인되었습니다!", Toast.LENGTH_SHORT).show()
                    saveUserAccountToDatabase(refreshedUser, currentNicknameForDb)
                } else {
                    Toast.makeText(this, "아직 이메일 인증이 완료되지 않았습니다. 메일함을 확인해주세요.", Toast.LENGTH_LONG).show()
                    btnCheckVerification.isEnabled = true
                    tvVerificationMessage.text = "입력하신 이메일로 인증 메일을 발송했습니다.\n메일을 확인하고 인증을 완료해주세요."
                }
            } else {

                Toast.makeText(this, "인증 상태 확인에 실패했습니다: ${reloadTask.exception?.message}", Toast.LENGTH_LONG).show()
                btnCheckVerification.isEnabled = true
                tvVerificationMessage.text = "입력하신 이메일로 인증 메일을 발송했습니다.\n메일을 확인하고 인증을 완료해주세요."
            }
        } ?: run {

            Toast.makeText(this, "사용자 정보를 찾을 수 없습니다. 다시 시도해주세요.", Toast.LENGTH_LONG).show()
            formContainer.visibility = View.VISIBLE
            layoutEmailVerification.visibility = View.GONE
            setFormEnabled(true)
            btnCheckVerification.isEnabled = true
            tvVerificationMessage.text = "입력하신 이메일로 인증 메일을 발송했습니다.\n메일을 확인하고 인증을 완료해주세요."
        }
    }

    private fun saveUserAccountToDatabase(firebaseUser: FirebaseUser, nickname: String?) {
        val uid = firebaseUser.uid
        val email = firebaseUser.email

        if (nickname == null) {

            Toast.makeText(this, "닉네임 정보가 없어 사용자 정보를 저장할 수 없습니다.", Toast.LENGTH_LONG).show()
            // 이 경우 사용자에게 다시 닉네임 입력을 요청하거나, 가입 절차를 안내해야 할 수 있습니다.
            formContainer.visibility = View.VISIBLE // 가입 폼 다시 표시
            layoutEmailVerification.visibility = View.GONE
            setFormEnabled(true)
            return
        }

        val account = UserAccount(uid, email, nickname)

        // Firebase Realtime Database에 사용자 정보 저장
        // 경로: UserAccount -> UID -> {idToken, emailId, nickname}
        mDatabaseRef.child(uid).setValue(account)
            .addOnSuccessListener {

                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                // 회원가입 성공 후 로그인 화면으로 이동 또는 메인 화면으로 이동
                val intent = Intent(this, LoginNew3Activity::class.java) // LoginActivity는 실제 로그인 액티비티 이름으로 변경
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // 이전 액티비티 스택 제거
                startActivity(intent)
                finish() // 현재 RegisterActivity 종료
            }
            .addOnFailureListener { e ->

                Toast.makeText(this, "데이터베이스 저장에 실패했습니다: ${e.message}", Toast.LENGTH_LONG).show()
                // 실패 시 사용자에게 다시 시도하도록 안내하거나, 수동으로 데이터를 삭제하는 등의 후처리 필요 가능성
                btnCheckVerification.isEnabled = true
                tvVerificationMessage.text = "데이터베이스 저장에 실패했습니다. 다시 시도해주세요."
            }
    }

    private fun validateInput(email: String, nickname: String, pass: String, confirmPass: String): Boolean {
        if (email.isEmpty()) {
            etEmail.error = "이메일을 입력해주세요."
            etEmail.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "올바른 이메일 형식을 입력해주세요."
            etEmail.requestFocus()
            return false
        }
        if (nickname.isEmpty()) {
            etNickname.error = "닉네임을 입력해주세요."
            etNickname.requestFocus()
            return false
        }
        // 닉네임 유효성 검사 추가 (예: 길이, 특수문자 등)
        // if (nickname.length < 2 || nickname.length > 10) {
        //     etNickname.error = "닉네임은 2자 이상 10자 이하로 입력해주세요."
        //     etNickname.requestFocus()
        //     return false
        // }
        if (pass.isEmpty()) {
            etPassword.error = "비밀번호를 입력해주세요."
            etPassword.requestFocus()
            return false
        }
        if (pass.length < 6) {
            etPassword.error = "비밀번호는 6자 이상 입력해주세요."
            etPassword.requestFocus()
            return false
        }
        if (confirmPass.isEmpty()) {
            etPasswordConfirm.error = "비밀번호 확인을 입력해주세요."
            etPasswordConfirm.requestFocus()
            return false
        }
        if (pass != confirmPass) {
            etPasswordConfirm.error = "비밀번호가 일치하지 않습니다."
            etPasswordConfirm.requestFocus()
            return false
        }
        return true
    }

    private fun setFormEnabled(enabled: Boolean) {
        etEmail.isEnabled = enabled
        etNickname.isEnabled = enabled
        etPassword.isEnabled = enabled
        etPasswordConfirm.isEnabled = enabled
        btnRequestRegister.isEnabled = enabled
    }
}
=======
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
            val intent = Intent(this, LoginNew3Activity::class.java)
            startActivity(intent)
            finish()}


    }
}
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17

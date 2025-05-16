package com.example.firebaseregister

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class UserAccount(
    var idToken: String? = null,
    var emailId: String? = null,
    var password: String? = null,
    var nickname: String? = null
)
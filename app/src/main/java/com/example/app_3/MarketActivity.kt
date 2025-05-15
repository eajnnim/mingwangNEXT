package com.example.app_3

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.app_3.databinding.ActivityMarketBinding
import android.util.Log

class MarketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.title = "그린마켓"

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)

        // 뒤로가기 아이콘 클릭 시
        toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.scroll.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            Log.d("SCROLL", "y = $scrollY")
            // scrollY 에 따라 FAB 숨기기/보이기 같은 동작도 여기서…
        }
    }
}
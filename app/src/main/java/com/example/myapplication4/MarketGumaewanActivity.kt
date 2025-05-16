package com.example.myapplication4

import android.os.Bundle
import android.widget.ImageView
import android.os.Looper
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent



class MarketGumaewanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_gumaewan)

        val imageRes = intent.getIntExtra("imageRes", 0)
        val name = intent.getStringExtra("menuName") ?: ""
        val tag = intent.getStringExtra("tag") ?: ""
        val price = intent.getStringExtra("price") ?: ""

        val imageView = findViewById<ImageView>(R.id.finalImage)
        val nameView = findViewById<TextView>(R.id.finalmenuName)
        val tagView = findViewById<TextView>(R.id.finalmenuTag)
        val priceView = findViewById<TextView>(R.id.finalmenuPrice)

        imageView.setImageResource(imageRes)
        nameView.text = name
        tagView.text = tag
        priceView.text = price

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MarketListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }, 3000)
    }
}

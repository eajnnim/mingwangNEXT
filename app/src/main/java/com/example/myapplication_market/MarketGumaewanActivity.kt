package com.example.myapplication_market

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

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
    }
}

package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.ImageView
import android.widget.TextView

class MarketDiaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_diary)

        findViewById<View>(R.id.left_half).setOnClickListener {
            startActivity(Intent(this, MarketListActivity::class.java))
            finish()
        }

        loadPurchases()
    }

    private fun loadPurchases() {
        val container = findViewById<LinearLayout>(R.id.purchaseContainer)
        val sharedPref = getSharedPreferences("diary", MODE_PRIVATE)
        val size = sharedPref.getInt("history_size", 0)

        for (i in 0 until size) {
            val record = sharedPref.getString("history_$i", null) ?: continue
            val parts = record.split("|")
            if (parts.size != 2) continue

            val imageName = parts[0]
            val time = parts[1]
            val resId = resources.getIdentifier(imageName, "drawable", packageName)

            val view = layoutInflater.inflate(R.layout.item_purchase, container, false)
            val imageView = view.findViewById<ImageView>(R.id.purchaseImage)
            val timeView = view.findViewById<TextView>(R.id.purchaseTime)

            imageView.setImageResource(resId)
            timeView.text = time

            container.addView(view)
        }
    }
}

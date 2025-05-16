package com.example.myapplication_market

import android.widget.ImageView
import com.example.myapplication.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MarketListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_list) // XML 파일명 맞춰줘 (예: activity_market.xml)

        // 오른쪽 절반 View에 클릭 리스너 설정
        val rightHalfView = findViewById<View>(R.id.right_half)
        rightHalfView.setOnClickListener {
            val intent = Intent(this, MarketDiaryActivity::class.java)
            startActivity(intent)
        }

        // 아메리카노 클릭 시 구매화면으로 이동
        findViewById<ImageView>(R.id.market_americano).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_americano_original)
                putExtra("menuName", "아이스 아메리카노")
                putExtra("tag", "스타벅스")
                putExtra("price", "5000")
            }
            startActivity(intent)
        }

        // 라떼 클릭 시 구매화면으로 이동
        findViewById<ImageView>(R.id.market_latte).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_latte_original)
                putExtra("menuName", "아이스 카페라떼")
                putExtra("tag", "스타벅스")
                putExtra("price", "5500")
            }
            startActivity(intent)
        }

        // 자바칩 클릭 시 구매화면으로 이동
        findViewById<ImageView>(R.id.market_java).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_java_original)
                putExtra("menuName", "자바 칩 프라푸치노")
                putExtra("tag", "스타벅스")
                putExtra("price", "6500")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_cass).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_cass_origianl)
                putExtra("menuName", "부드러운 생크림 카스텔라")
                putExtra("tag", "스타벅스")
                putExtra("price", "7500")
            }
            startActivity(intent)
        }

        // 싸이세트 클릭 시 구매화면으로 이동
        findViewById<ImageView>(R.id.market_psyset).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_psyset_original)
                putExtra("menuName", "싸이버거 세트")
                putExtra("tag", "맘스터치")
                putExtra("price", "7500")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_psydan).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_psydan_original)
                putExtra("menuName", "싸이버거 단품")
                putExtra("tag", "맘스터치")
                putExtra("price", "6000")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_vita).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_vita_original)
                putExtra("menuName", "비타500")
                putExtra("tag", "GS 25")
                putExtra("price", "1500")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_bull).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_bull_original)
                putExtra("menuName", "불닭볶음면")
                putExtra("tag", "GS 25")
                putExtra("price", "2000")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_oliv5000).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_oliv_original)
                putExtra("menuName", "올리브영 5000원 상품권")
                putExtra("tag", "올리브영")
                putExtra("price", "6000")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_oliv10000).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_oliv_original)
                putExtra("menuName", "올리브영 10000원 상품권")
                putExtra("tag", "올리브영")
                putExtra("price", "11500")
            }
            startActivity(intent)
        }

    }
}

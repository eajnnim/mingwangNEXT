package com.example.myapplication4

import android.widget.ImageView
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
                putExtra("imageName", "market_americano")
                putExtra("menuName", "아이스 아메리카노")
                putExtra("menuTag", "스타벅스")
                putExtra("menuPrice", "5000")
            }
            startActivity(intent)
        }

        // 라떼 클릭 시 구매화면으로 이동
        findViewById<ImageView>(R.id.market_latte).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_latte_original)
                putExtra("imageName", "market_latte")
                putExtra("menuName", "아이스 카페라떼")
                putExtra("menuTag", "스타벅스")
                putExtra("menuPrice", "5500")
            }
            startActivity(intent)
        }

        // 자바칩 클릭 시 구매화면으로 이동
        findViewById<ImageView>(R.id.market_java).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_java_original)
                putExtra("imageName", "market_java")
                putExtra("menuName", "자바 칩 프라푸치노")
                putExtra("menuTag", "스타벅스")
                putExtra("menuPrice", "6500")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_cass).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_cass_origianl)
                putExtra("imageName", "market_cass")
                putExtra("menuName", "부드러운 생크림 카스텔라")
                putExtra("menuTag", "스타벅스")
                putExtra("menuPrice", "7500")
            }
            startActivity(intent)
        }

        // 싸이세트 클릭 시 구매화면으로 이동
        findViewById<ImageView>(R.id.market_psyset).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_psyset_original)
                putExtra("imageName", "market_psyset")
                putExtra("menuName", "싸이버거 세트")
                putExtra("menuTag", "맘스터치")
                putExtra("menuPrice", "7500")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_psydan).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_psydan_original)
                putExtra("imageName", "market_psydan")
                putExtra("menuName", "싸이버거 단품")
                putExtra("menuTag", "맘스터치")
                putExtra("menuPrice", "6000")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_vita).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_vita_original)
                putExtra("imageName", "market_vita")
                putExtra("menuName", "비타500")
                putExtra("menuTag", "GS 25")
                putExtra("menuPrice", "1500")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_bull).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_bull_original)
                putExtra("imageName", "market_bull")
                putExtra("menuName", "불닭볶음면")
                putExtra("menuTag", "GS 25")
                putExtra("menuPrice", "2000")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_oliv5000).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_oliv_original)
                putExtra("imageName", "market_oliv5000")
                putExtra("menuName", "올리브영 5000원 상품권")
                putExtra("menuTag", "올리브영")
                putExtra("menuPrice", "6000")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.market_oliv10000).setOnClickListener {
            val intent = Intent(this, MarketGumaeActivity::class.java).apply {
                putExtra("imageResId", R.drawable.market_oliv_original)
                putExtra("imageName", "market_oliv10000")
                putExtra("menuName", "올리브영 10000원 상품권")
                putExtra("menuTag", "올리브영")
                putExtra("menuPrice", "11500")
            }
            startActivity(intent)
        }

    }
}

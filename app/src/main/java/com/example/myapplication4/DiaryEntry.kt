package com.example.myapplication4
import com.example.myapplication4.R

data class DiaryEntry(
    val dateTime: String,
    val location: String,
    val throwItem: String,
    val exp: Int,
    val coin: Int,
    val iconResId: Int = R.drawable.green_market
)

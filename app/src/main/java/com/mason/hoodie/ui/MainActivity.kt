package com.mason.hoodie.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ActivityMainBinding

/**
 * Created by mason-hong on 28/11/2018.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val viewPages = listOf(
            SearchPageView(this),
            SearchPageView(this),
            SearchPageView(this),
            SearchPageView(this),
            SearchPageView(this)
        )
        binding.viewPager.adapter = MainPagerAdapter(viewPages)
    }
}

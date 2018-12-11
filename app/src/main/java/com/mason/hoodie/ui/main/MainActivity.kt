package com.mason.hoodie.ui.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ActivityMainBinding
import com.mason.hoodie.ui.favorite.FavoritePageView
import com.mason.hoodie.ui.search.SearchPageView

/**
 * Created by mason-hong on 28/11/2018.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val viewPages = listOf(
                FavoritePageView(this),
                SearchPageView(this)
        )
        binding.viewPager.adapter = MainPagerAdapter(viewPages)
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}

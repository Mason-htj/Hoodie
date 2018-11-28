package com.mason.hoodie.ui

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ActivityMainBinding
import com.mason.hoodie.presentation.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by mason-hong on 28/11/2018.
 */
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private val adapter = DocumentListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.liveRepo.observe(this, Observer {
            adapter.setItems(it ?: emptyList())
        })
    }
}

package com.mason.hoodie.ui.search

import android.arch.lifecycle.Observer
import android.support.v7.widget.DividerItemDecoration
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ViewSearchPageBinding
import com.mason.hoodie.common.inject
import com.mason.hoodie.presentation.FavoritesViewModel
import com.mason.hoodie.presentation.SearchViewModel
import com.mason.hoodie.ui.SearchResult
import com.mason.hoodie.ui.base.BasePageView
import com.mason.hoodie.ui.main.MainActivity

/**
 * Created by mason-hong on 29/11/2018.
 */
class SearchPageView(activity: MainActivity) : BasePageView(activity), SearchResultAdapter.Listener {
    private val searchViewModel: SearchViewModel by inject()
    private val favoritesViewModel: FavoritesViewModel by inject()
    private val adapter = SearchResultAdapter(this)

    override fun onCreate() {
        super.onCreate()
        val binding = bindingView<ViewSearchPageBinding>(R.layout.view_search_page)
        binding.viewModel = searchViewModel
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        searchViewModel.liveRepo.observe(this, Observer {
            adapter.setItems(it ?: emptyList())
        })
    }

    override fun getTitle(): String = "Search"

    override fun markFavorite(item: SearchResult) {
        favoritesViewModel.markFavorite(item.document)
    }

    override fun unmarkFavorite(item: SearchResult) {
        favoritesViewModel.unmarkFavorite(item.document.group, item.document.artifact)
    }

    override fun onDestroy() {
        searchViewModel.onDestroy()
    }
}
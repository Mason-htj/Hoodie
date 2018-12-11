package com.mason.hoodie.ui.favorite

import android.arch.lifecycle.Observer
import android.support.v7.widget.DividerItemDecoration
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ViewFavoritesPageBinding
import com.mason.hoodie.common.inject
import com.mason.hoodie.data.local.Favorites
import com.mason.hoodie.presentation.FavoritesViewModel
import com.mason.hoodie.ui.base.BasePageView
import com.mason.hoodie.ui.main.MainActivity

/**
 * Created by mason-hong on 29/11/2018.
 */
class FavoritePageView(activity: MainActivity) : BasePageView(activity), FavoritesAdapter.Listener {
    private val viewModel: FavoritesViewModel by inject()
    private val adapter = FavoritesAdapter(this)

    override fun onCreate() {
        val binding = bindingView<ViewFavoritesPageBinding>(R.layout.view_favorites_page)
        binding.viewModel = viewModel
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        viewModel.favorites.observe(this, Observer {
            adapter.setItems(it ?: emptyList())
        })
        viewModel.loadFavorites()
    }

    override fun onDeleteClick(item: Favorites) {
        viewModel.unmarkFavorite(item.group, item.artifact)
    }

    override fun getTitle(): String = "Favorite"

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
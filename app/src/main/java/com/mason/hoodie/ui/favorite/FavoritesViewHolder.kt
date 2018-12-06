package com.mason.hoodie.ui.favorite

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ViewholderFavoritesBinding
import com.mason.hoodie.data.local.Favorites

/**
 * Created by mason-hong on 05/12/2018.
 */
class FavoritesViewHolder private constructor(
    private val binding: ViewholderFavoritesBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            parent: ViewGroup,
            listener: FavoritesAdapter.Listener
        ): FavoritesViewHolder {
            val binding = DataBindingUtil.inflate<ViewholderFavoritesBinding>(
                LayoutInflater.from(parent.context),
                R.layout.viewholder_favorites,
                parent,
                false
            )
            binding.listener = listener
            return FavoritesViewHolder(binding)
        }
    }

    fun setItem(item: Favorites?) {
        binding.item = item
        binding.executePendingBindings()
    }
}
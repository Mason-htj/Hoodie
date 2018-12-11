package com.mason.hoodie.ui.search

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ViewholderRepositoryBinding
import com.mason.hoodie.ui.SearchResult

/**
 * Created by mason-hong on 05/12/2018.
 */
class SearchResultViewHolder private constructor(
        private val binding: ViewholderRepositoryBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
                parent: ViewGroup,
                listener: SearchResultAdapter.Listener
        ): SearchResultViewHolder {
            val binding = DataBindingUtil.inflate<ViewholderRepositoryBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.viewholder_repository,
                    parent,
                    false
            )
            binding.listener = listener
            return SearchResultViewHolder(binding)
        }
    }

    fun setItem(item: SearchResult?) {
        binding.item = item
        binding.executePendingBindings()
    }
}
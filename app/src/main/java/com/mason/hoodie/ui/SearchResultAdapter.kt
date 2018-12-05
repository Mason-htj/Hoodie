package com.mason.hoodie.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ViewholderRepositoryBinding

/**
 * Created by mason-hong on 28/11/2018.
 */
class SearchResultAdapter : RecyclerView.Adapter<SearchResultViewHolder>() {
    private val items = ArrayList<SearchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder.create(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: SearchResultViewHolder, position: Int) {
        viewHolder.setItem(items[position])
    }

    fun setItems(items: List<SearchResult>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class SearchResultViewHolder private constructor(
    private val binding: ViewholderRepositoryBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): SearchResultViewHolder =
            SearchResultViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.viewholder_repository,
                    parent,
                    false
                )
            )
    }

    fun setItem(item: SearchResult?) {
        binding.item = item
        binding.executePendingBindings()
    }
}
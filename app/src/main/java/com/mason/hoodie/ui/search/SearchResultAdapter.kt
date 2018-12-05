package com.mason.hoodie.ui.search

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.mason.hoodie.ui.SearchResult

/**
 * Created by mason-hong on 28/11/2018.
 */
class SearchResultAdapter(private val listener: Listener) : RecyclerView.Adapter<SearchResultViewHolder>() {
    interface Listener {
        fun markFavorite(item: SearchResult)
        fun unmarkFavorite(item: SearchResult)
    }

    private val items = ArrayList<SearchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder.create(parent, listener)

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
package com.mason.hoodie.ui.favorite

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.mason.hoodie.data.local.Favorites

/**
 * Created by mason-hong on 28/11/2018.
 */
class FavoritesAdapter(private val listener: Listener) : RecyclerView.Adapter<FavoritesViewHolder>() {
    interface Listener {
    }

    private val items = ArrayList<Favorites>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder.create(parent, listener)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: FavoritesViewHolder, position: Int) {
        viewHolder.setItem(items[position])
    }

    fun setItems(items: List<Favorites>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
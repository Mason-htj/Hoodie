@file:JvmName("DataBindingUtils")

package com.mason.hoodie.ui

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import com.mason.hoodie.data.Document

/**
 * Created by mason-hong on 28/11/2018.
 */
@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, list: ObservableArrayList<Document>) {
    val adapter = recyclerView.adapter
    if (adapter is DocumentListAdapter) {
        adapter.setItems(list)
    }
}
package com.mason.hoodie.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kakao.mason.hoodie.R
import com.kakao.mason.hoodie.databinding.ViewholderRepositoryBinding
import com.mason.hoodie.data.Document

/**
 * Created by mason-hong on 28/11/2018.
 */
class DocumentListAdapter : RecyclerView.Adapter<DocumentViewHolder>() {
    private val items = ArrayList<Document>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder =
        DocumentViewHolder.create(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: DocumentViewHolder, position: Int) {
        viewHolder.setItem(items[position])
    }

    fun setItems(items: List<Document>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class DocumentViewHolder private constructor(
    private val binding: ViewholderRepositoryBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): DocumentViewHolder =
            DocumentViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.viewholder_repository,
                    parent,
                    false
                )
            )
    }

    fun setItem(item: Document?) {
        binding.item = item
        binding.executePendingBindings()
    }
}
package com.mason.hoodie.ui

import com.mason.hoodie.data.Document

/**
 * Created by mason-hong on 05/12/2018.
 */
data class SearchResult(
    val document: Document,
    val isFavorite: Boolean
)
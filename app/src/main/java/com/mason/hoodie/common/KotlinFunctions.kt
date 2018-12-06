package com.mason.hoodie.common

import com.mason.hoodie.data.local.Favorites
import com.mason.hoodie.data.remote.Document
import com.mason.hoodie.ui.SearchResult
import com.mason.hoodie.ui.base.BaseView
import org.koin.android.ext.android.inject

/**
 * Created by mason-hong on 29/11/2018.
 */
inline fun <reified T : Any> BaseView.inject() = activity.inject<T>()

fun Document.hasFavorites(favorites: List<Favorites>): Boolean {
    favorites.forEach {
        if (it.group == this.group && it.artifact == this.artifact) {
            return true
        }
    }
    return false
}
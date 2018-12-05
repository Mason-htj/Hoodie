package com.mason.hoodie.data

import android.arch.persistence.room.Entity

/**
 * Created by mason-hong on 05/12/2018.
 */
@Entity(
    primaryKeys = ["group", "artifact"]
)
data class Favorites(
    val group: String,
    val artifact: String,
    val insertedTime: Long,
    var version: String
)
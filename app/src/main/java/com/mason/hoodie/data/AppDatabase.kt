package com.mason.hoodie.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by mason-hong on 05/12/2018.
 */
@Database(
    entities = [Favorites::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}
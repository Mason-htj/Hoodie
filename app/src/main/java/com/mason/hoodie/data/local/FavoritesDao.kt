package com.mason.hoodie.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single

/**
 * Created by mason-hong on 05/12/2018.
 */
@Dao
interface FavoritesDao {
    @Query("SELECT * FROM Favorites")
    fun getAll(): Single<List<Favorites>>

    @Insert
    fun insert(vararg favorites: Favorites): List<Long>

    @Delete
    fun delete(vararg favorites: Favorites): Int
}
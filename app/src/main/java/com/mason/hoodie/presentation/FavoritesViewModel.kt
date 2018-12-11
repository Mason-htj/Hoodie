package com.mason.hoodie.presentation

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.mason.hoodie.data.local.AppDatabase
import com.mason.hoodie.data.local.Favorites
import com.mason.hoodie.data.remote.Document
import com.mason.hoodie.data.remote.MavenRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Created by mason-hong on 06/12/2018.
 */
class FavoritesViewModel(
    private val mavenRepo: MavenRepository,
    private val database: AppDatabase
) {
    val isLoading = ObservableBoolean(false)
    val resultSize = ObservableInt(-1)
    val favorites = MutableLiveData<List<Favorites>>()

    private val compositeDisposable = CompositeDisposable()

    fun loadFavorites() {
        isLoading.set(true)
        database.favoritesDao().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    resultSize.set(it.size)
                    favorites.value = it
                    isLoading.set(false)
                },
                {
                    isLoading.set(false)
                }
            ).addTo(compositeDisposable)
    }

    fun markFavorite(document: Document) {
        Completable.defer {
            database.favoritesDao().insert(Favorites(document.group, document.artifact, document.latestVersion))
            Completable.complete()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // 성공
                    loadFavorites()
                },
                {
                    // 실패
                }
            ).addTo(compositeDisposable)
    }

    fun unmarkFavorite(group: String, artifact: String) {
        Completable.defer {
            database.favoritesDao().delete(Favorites(group, artifact))
            Completable.complete()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // 성공
                    loadFavorites()
                },
                {
                    // 실패
                }
            ).addTo(compositeDisposable)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
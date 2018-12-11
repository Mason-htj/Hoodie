package com.mason.hoodie.presentation

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.mason.hoodie.common.hasFavorites
import com.mason.hoodie.data.local.AppDatabase
import com.mason.hoodie.data.local.Favorites
import com.mason.hoodie.data.remote.MavenRepository
import com.mason.hoodie.data.remote.MavenResponse
import com.mason.hoodie.ui.SearchResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Created by mason-hong on 29/11/2018.
 */
class SearchViewModel(
        private val mavenRepo: MavenRepository,
        private val database: AppDatabase
) {
    val isLoadingRepo = ObservableBoolean()
    val resultSize = ObservableInt(-1)
    val liveRepo = MutableLiveData<List<SearchResult>>()

    private val compositeDisposable = CompositeDisposable()

    fun search(query: String) {
        isLoadingRepo.set(true)
        Single.zip(
                mavenRepo.getArtifactsAndGroup(query),
                database.favoritesDao().getAll(),
                BiFunction<MavenResponse, List<Favorites>, Pair<Int, List<SearchResult>>> { response, favorites ->
                    Pair(
                            response.response.numFound,
                            response.response.docs.map { SearchResult(it, it.hasFavorites(favorites)) })
                }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            resultSize.set(it.first)
                            liveRepo.value = it.second
                            isLoadingRepo.set(false)
                        },
                        {
                            isLoadingRepo.set(false)
                        }
                ).addTo(compositeDisposable)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
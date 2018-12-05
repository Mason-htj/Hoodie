package com.mason.hoodie.presentation

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.mason.hoodie.data.Document
import com.mason.hoodie.data.MavenRepository
import com.mason.hoodie.ui.SearchResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Created by mason-hong on 29/11/2018.
 */
class SearchViewModel(
    private val mavenRepo: MavenRepository
) {
    val isLoadingRepo = ObservableBoolean()
    val resultSize = ObservableInt(-1)
    val liveRepo = MutableLiveData<List<SearchResult>>()

    private val repositories = ObservableArrayList<Document>()
    private val compositeDisposable = CompositeDisposable()

    fun search(query: String) {
        isLoadingRepo.set(true)
        mavenRepo.getArtifactsAndGroup(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    repositories.clear()
                    repositories.addAll(it.response.docs)
                    resultSize.set(it.response.numFound)
                    liveRepo.value = it.response.docs.map { doc -> SearchResult(doc, false) }
                    isLoadingRepo.set(false)
                },
                {
                    isLoadingRepo.set(false)
                }
            ).addTo(compositeDisposable)
    }
}
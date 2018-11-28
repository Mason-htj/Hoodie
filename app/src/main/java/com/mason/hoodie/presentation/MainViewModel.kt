package com.mason.hoodie.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.mason.hoodie.data.Document
import com.mason.hoodie.data.MavenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by mason-hong on 28/11/2018.
 */
class MainViewModel(
    private val mavenRepo: MavenRepository
) : ViewModel() {
    val isLoadingRepo = ObservableBoolean()
    val repositories = ObservableArrayList<Document>()
    val resultSize = ObservableInt(-1)

    val liveRepo = MutableLiveData<List<Document>>()

    fun search(query: String) {
        isLoadingRepo.set(true)
        GlobalScope.launch {
            try {
                val result = mavenRepo.getArtifactsAndGroup(query).await()
                launch(Dispatchers.Main) {
                    repositories.clear()
                    repositories.addAll(result.response.docs)
                    resultSize.set(result.response.numFound)
                    liveRepo.value = result.response.docs
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isLoadingRepo.set(false)
        }
    }
}
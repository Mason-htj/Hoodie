package com.mason.hoodie.data

import android.support.annotation.WorkerThread
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mason.hoodie.common.SIZE_DEFAULT
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by mason-hong on 28/11/2018.
 */
class MavenRepository {
    private val mavenService = Retrofit.Builder()
        .baseUrl("https://search.maven.org/")
        .client(
            OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(MavenService::class.java)

    @WorkerThread
    fun getGroups(group: String): Deferred<MavenResponse> =
        mavenService.searchRepositories("g:\"$group\"", SIZE_DEFAULT)

    @WorkerThread
    fun getArtifacts(artifact: String): Deferred<MavenResponse> =
        mavenService.searchRepositories("a:\"$artifact\"", SIZE_DEFAULT)

    @WorkerThread
    fun getArtifactsWithGroup(group: String, artifact: String): Deferred<MavenResponse> =
        mavenService.searchRepositories("g:\"$group\" AND a:\"$artifact\"", SIZE_DEFAULT)

    @WorkerThread
    fun getArtifactsAndGroup(name: String): Deferred<MavenResponse> =
        mavenService.searchRepositories(name, SIZE_DEFAULT)
}
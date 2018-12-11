package com.mason.hoodie.data.remote

import com.mason.hoodie.common.SIZE_DEFAULT
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by mason-hong on 28/11/2018.
 */
class MavenRepository {
    private val mavenService = Retrofit.Builder()
            .baseUrl("https://search.maven.org/")
            .client(
                    OkHttpClient.Builder()
                            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                            .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MavenService::class.java)

    fun getGroups(group: String): Single<MavenResponse> =
            mavenService.searchRepositories("g:\"$group\"", SIZE_DEFAULT)

    fun getArtifacts(artifact: String): Single<MavenResponse> =
            mavenService.searchRepositories("a:\"$artifact\"", SIZE_DEFAULT)

    fun getArtifactsWithGroup(group: String, artifact: String): Single<MavenResponse> =
            mavenService.searchRepositories("g:\"$group\" AND a:\"$artifact\"", SIZE_DEFAULT)

    fun getArtifactsAndGroup(name: String): Single<MavenResponse> =
            mavenService.searchRepositories(name, SIZE_DEFAULT)
}
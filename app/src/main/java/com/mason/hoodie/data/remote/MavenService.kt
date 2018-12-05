package com.mason.hoodie.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mason-hong on 28/11/2018.
 */
interface MavenService {
    @GET("solrsearch/select?wt=json")
    fun searchRepositories(
        @Query("q") group: String,
        @Query("row") size: Int
    ): Single<MavenResponse>
}
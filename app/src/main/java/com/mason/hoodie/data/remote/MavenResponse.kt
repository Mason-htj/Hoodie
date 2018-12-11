package com.mason.hoodie.data.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by mason-hong on 28/11/2018.
 */

data class MavenResponse(
        val responseHeader: MavenHeader,
        val response: RepoResponse
)

data class MavenHeader(
        val status: Int,
        val QTime: Int,
        val params: Any
)

data class RepoResponse(
        val numFound: Int,
        val start: Int,
        val docs: List<Document>
)

data class Document(
        val id: String,
        @SerializedName("g") val group: String,
        @SerializedName("a") val artifact: String,
        val latestVersion: String,
        val p: String,
        val timestamp: Long,
        val ec: List<String>,
        val tags: List<String>
)
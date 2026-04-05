package com.nak5.exemplar.network

import com.nak5.exemplar.network.data.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): List<Repo>
}
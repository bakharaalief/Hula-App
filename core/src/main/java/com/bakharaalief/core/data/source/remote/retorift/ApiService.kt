package com.bakharaalief.core.data.source.remote.retorift

import com.bakharaalief.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getListMovie(@Query("api_key") apiKey: String): MovieResponse
}
package com.example.doodleblueassignment.data.api

import com.example.doodleblueassignment.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "1610d7f7d24d4b6181840df54dacbcb8"
    ): Response<NewsResponse>
}
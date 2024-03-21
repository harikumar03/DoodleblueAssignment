package com.example.doodleblueassignment.data.datasource

import com.example.doodleblueassignment.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    suspend fun getNewsHeadlines(country: String): Response<NewsResponse>
}
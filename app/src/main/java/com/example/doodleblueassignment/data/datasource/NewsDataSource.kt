package com.example.doodleblueassignment.data.datasource

import com.example.doodleblueassignment.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    fun getNewsHeadlines(country: String): Response<NewsResponse>
}
package com.example.doodleblueassignment.data.datasource

import com.example.doodleblueassignment.data.api.ApiService
import com.example.doodleblueassignment.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val apiService: ApiService
): NewsDataSource {
    override suspend fun getNewsHeadlines(country: String): Response<NewsResponse> {
        return apiService.getNewsHeadline(country)
    }
}
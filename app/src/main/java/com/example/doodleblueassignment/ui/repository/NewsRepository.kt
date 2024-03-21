package com.example.doodleblueassignment.ui.repository

import com.example.doodleblueassignment.data.datasource.NewsDataSource
import com.example.doodleblueassignment.data.entity.NewsResponse
import com.example.doodleblueassignment.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

    suspend fun getNewsHeadlines(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())
            val response = newsDataSource.getNewsHeadlines(country)
            if (response.isSuccessful && response.body() != null){
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error Fetching news data"))
            }
        }.catch { exception->
            emit(ResourceState.Error(exception.localizedMessage ?: "Server Error.Please try again Later"))
        }
    }
}
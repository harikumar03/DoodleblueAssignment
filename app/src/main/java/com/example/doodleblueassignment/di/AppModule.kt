package com.example.doodleblueassignment.di

import com.example.doodleblueassignment.data.api.ApiService
import com.example.doodleblueassignment.data.datasource.DataSourceImpl
import com.example.doodleblueassignment.data.datasource.NewsDataSource
import com.example.doodleblueassignment.ui.repository.NewsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
     fun retrofitInstance() : Retrofit {
         val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
             level = HttpLoggingInterceptor.Level.BASIC
         }
         val httpClient = OkHttpClient().newBuilder().apply {
             addInterceptor(httpLoggingInterceptor)
         }
         httpClient.apply {
             readTimeout(timeout = 60, TimeUnit.SECONDS)
         }
         val moshi = Moshi.Builder()
             .add(KotlinJsonAdapterFactory()).build()

         return Retrofit.Builder()
             .baseUrl("https://newsapi.org/")
             .client(httpClient.build())
             .addConverterFactory(MoshiConverterFactory.create(moshi))
             .build()
     }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsDataSource(apiService: ApiService): NewsDataSource {
        return DataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsDataSource: NewsDataSource): NewsRepository {
        return NewsRepository(newsDataSource)
    }


}
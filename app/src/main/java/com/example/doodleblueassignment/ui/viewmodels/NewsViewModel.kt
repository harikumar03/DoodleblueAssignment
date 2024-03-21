package com.example.doodleblueassignment.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodleblueassignment.data.entity.NewsResponse
import com.example.doodleblueassignment.ui.repository.NewsRepository
import com.example.doodleblueassignment.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _news: MutableStateFlow<ResourceState<NewsResponse>> = MutableStateFlow(ResourceState.Loading())
    val news: StateFlow<ResourceState<NewsResponse>> = _news

    init {
        getNewsHeadlines("us")
    }
    fun getNewsHeadlines(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNewsHeadlines(country).collectLatest { newsResponse->
                _news.value = newsResponse
            }
        }
    }
}
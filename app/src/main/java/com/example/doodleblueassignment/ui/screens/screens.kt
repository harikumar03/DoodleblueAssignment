package com.example.doodleblueassignment.ui.screens

import android.graphics.Color.BLUE
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.doodleblueassignment.ui.Loader
import com.example.doodleblueassignment.ui.SearchBar
import com.example.doodleblueassignment.ui.viewmodels.NewsViewModel
import com.example.doodleblueassignment.util.ResourceState


@Composable
fun HomeScreen(newsViewModel: NewsViewModel = hiltViewModel()){
    val newsRes by newsViewModel.news.collectAsState()
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Blue) {
        when(newsRes) {
            is ResourceState.Loading -> {
                    Loader()
            }
            is ResourceState.Success -> {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    SearchBar()
                }
            }
            is ResourceState.Error -> {

            }

        }
    }
}


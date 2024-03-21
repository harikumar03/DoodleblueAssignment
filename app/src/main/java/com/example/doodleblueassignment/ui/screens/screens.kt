package com.example.doodleblueassignment.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.doodleblueassignment.ui.AlignYourBodyRow
import com.example.doodleblueassignment.ui.Loader
import com.example.doodleblueassignment.ui.SearchBar
import com.example.doodleblueassignment.ui.viewmodels.NewsViewModel
import com.example.doodleblueassignment.util.ResourceState
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.fragment.app.FragmentManager.TAG
import com.example.doodleblueassignment.ui.FavoriteCollectionCard

@Composable
fun HomeScreen(newsViewModel: NewsViewModel = hiltViewModel()){
    val newsRes by newsViewModel.news.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        when(newsRes) {
            is ResourceState.Loading -> {
                    Loader()
            }
            is ResourceState.Success -> {
                val response = (newsRes as ResourceState.Success).data
                Column{
                    Spacer(Modifier.height(16.dp))
                    SearchBar()
                    AlignYourBodyRow()
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                       items(response.articles){item->
                           FavoriteCollectionCard(item.urlToImage ?: "null",item.title ?: "null",item.description ?: "null")

                       }
                    }
                }
            }
            is ResourceState.Error -> {
                Log.d("TAG","Error ")
            }

        }
    }
}


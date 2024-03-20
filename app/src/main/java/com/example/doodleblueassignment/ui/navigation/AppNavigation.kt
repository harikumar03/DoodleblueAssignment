package com.example.doodleblueassignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doodleblueassignment.ui.navigation.Routes.HOME_SCREEN
import com.example.doodleblueassignment.ui.screens.HomeScreen

@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = HOME_SCREEN) {

        composable(HOME_SCREEN){
            HomeScreen()
        }
    }
}

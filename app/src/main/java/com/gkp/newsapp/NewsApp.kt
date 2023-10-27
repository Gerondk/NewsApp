package com.gkp.newsapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gkp.newsapp.presentation.components.BottomNavigation
import com.gkp.newsapp.presentation.navigation.NewsAppGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(onItemClicked = { navController.navigate(it) })
        }
    ) { paddingValues ->
        NewsAppGraph(
            modifier = Modifier.padding(paddingValues),
            navController = navController
        )
    }
}
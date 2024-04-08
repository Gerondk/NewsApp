package com.gkp.newsapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gkp.newsapp.presentation.components.BottomNavigation
import com.gkp.newsapp.presentation.navigation.NewsAppGraph
import com.gkp.newsapp.presentation.navigation.navigateToTopDestination
import com.gkp.newsapp.presentation.navigation.topDestinationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val shouldShowBottomNavigationBar by remember(currentDestination) {
        derivedStateOf {
            currentDestination?.route in topDestinationRoutes
        }
    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomNavigationBar) {
                BottomNavigation(
                    onItemClicked = {
                        navController.navigateToTopDestination(it)
                    },
                    currentDestination
                )
            }
        }
    ) { paddingValues ->
        NewsAppGraph(
            modifier = Modifier.padding(paddingValues),
            navController = navController
        )
    }
}

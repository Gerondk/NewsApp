package com.gkp.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gkp.bookmarks.bookmarksScreen
import com.gkp.core.Screens
import com.gkp.home.presentation.homeScreen

@Composable
fun NewsAppGraph(
    modifier: Modifier,
    navController: NavHostController,
    onDarkTheme: (Boolean?) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        homeScreen(
            navController = navController,
            onDarkTheme = onDarkTheme
        )
        bookmarksScreen()
    }
}

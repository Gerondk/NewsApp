package com.gkp.newsapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.gkp.core.Screens

data class BottomNavigationItemModel(val title: String, val icon: ImageVector)

val bottomNavigationItems = listOf(
    BottomNavigationItemModel(
        title = Screens.HomeScreen.route,
        icon = Icons.Filled.Home
    ),
    BottomNavigationItemModel(
        title = Screens.BookmarksScreen.route,
        icon = Icons.Filled.Bookmark
    )
)

val topDestinationRoutes = bottomNavigationItems.map { it.title }
fun NavHostController.navigateToTopDestination(topDestinationRoute: String) =
    navigate(topDestinationRoute) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

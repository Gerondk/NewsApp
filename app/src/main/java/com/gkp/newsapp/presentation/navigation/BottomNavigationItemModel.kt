package com.gkp.newsapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.gkp.core.Screens

data class BottomNavigationItemModel(val title: String, val icon: ImageVector)

val bottomNavigationItems = listOf(
    BottomNavigationItemModel(
        title = Screens.HomeScreen.route,
        icon = Icons.Filled.Home
    ),
    BottomNavigationItemModel(
        title = Screens.BookmarksScreen.route,
        icon = Icons.Filled.Favorite
    )
)

package com.gkp.newsapp.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.gkp.newsapp.presentation.navigation.bottomNavigationItems

@Composable
fun BottomNavigation(
    onItemClicked: (String) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar {
        bottomNavigationItems.forEachIndexed { _, item ->
            NavigationBarItem(
                label = { Text(text = item.title) },
                selected = currentDestination?.hierarchy?.any {
                    it.route == item.title
                } == true,
                onClick = {
                    onItemClicked(item.title)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue
                )
            )
        }
    }
}

package com.gkp.newsapp.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.gkp.newsapp.presentation.navigation.bottomNavigationItems

@Composable
fun BottomNavigation(onItemClicked: (String) -> Unit) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    NavigationBar {
        bottomNavigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                label = { Text(text = item.title) },
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
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
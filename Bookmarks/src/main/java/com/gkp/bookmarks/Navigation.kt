package com.gkp.bookmarks

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gkp.core.Screens

fun NavGraphBuilder.bookmarksScreen() {
    composable(route = Screens.BookmarksScreen.route) {
        val bookmarksViewModel = hiltViewModel<BookmarksViewModel>()

        Bookmarks(
            bookmarksViewModel = bookmarksViewModel,
            onDeleteBookmark = bookmarksViewModel::deleteBookmarkedArticle
        )
    }
}

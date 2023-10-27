package com.gkp.bookmarks

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gkp.core.Screens

fun NavGraphBuilder.bookmarksScreen() {
    composable(route = Screens.BookmarksScreen.route) {
        val bookmarksViewModel = hiltViewModel<BookmarksViewModel>()
        val uiState by bookmarksViewModel.uiState.collectAsStateWithLifecycle()
        Bookmarks(
            uiState = uiState,
            onDeleteBookmark = bookmarksViewModel::deleteBookmarkedArticle
        )
    }
}

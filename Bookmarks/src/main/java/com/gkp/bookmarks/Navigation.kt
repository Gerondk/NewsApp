package com.gkp.bookmarks

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gkp.core.Screens

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bookmarksScreen() {
    composable(route = Screens.BookmarksScreen.route) {
        val bookmarksViewModel = hiltViewModel<BookmarksViewModel>()

        Bookmarks(
            bookmarksViewModel = bookmarksViewModel,
            onDeleteBookmark = bookmarksViewModel::deleteBookmarkedArticle
        )
    }
}

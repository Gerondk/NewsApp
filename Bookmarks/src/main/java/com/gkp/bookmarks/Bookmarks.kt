package com.gkp.bookmarks

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gkp.bookmarks.components.BookmarkedArticlesList
import com.gkp.core.domain.NewsArticle
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Bookmarks(
    bookmarksViewModel: BookmarksViewModel,
    onDeleteBookmark: (NewsArticle) -> Unit
) {
    val uiState by bookmarksViewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        bookmarksViewModel.bookmarksEvents.collectLatest { event ->
            when (event) {
                is BookMarkEvents.OnDeleteBookmarkedArticle -> {
                    snackBarHostState.showSnackbar(
                        message = "NewsArticle: ${event.newsArticle.title} deleted",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Long
                    )
                }
            }
        }
    }
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Bookmarks",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                },
                scrollBehavior = topAppBarScrollBehavior
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            when (uiState) {
                BookmarksUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is BookmarksUiState.Success -> {
                    BookmarkedArticlesList(
                        articles = (uiState as BookmarksUiState.Success).articles,
                        onDeleteBookmark = onDeleteBookmark
                    )
                }
            }
        }
    }
}

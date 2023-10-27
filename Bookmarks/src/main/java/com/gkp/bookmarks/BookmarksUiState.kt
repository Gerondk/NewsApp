package com.gkp.bookmarks

import com.gkp.core.domain.NewsArticle

sealed interface BookmarksUiState {
    object Loading : BookmarksUiState
    data class Success(val articles: List<NewsArticle>) : BookmarksUiState
}

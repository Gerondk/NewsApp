package com.gkp.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.core.domain.DeleteBookmarkedNewsArticleUseCase
import com.gkp.core.domain.GetBookmarkedArticles
import com.gkp.core.domain.NewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    getBookmarkedArticles: GetBookmarkedArticles,
    private val deleteBookmarkedNewsArticleUseCase: DeleteBookmarkedNewsArticleUseCase
) : ViewModel() {

    val uiState = getBookmarkedArticles().mapLatest { articles ->
        BookmarksUiState.Success(articles)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        BookmarksUiState.Loading
    )

    fun deleteBookmarkedArticle(newsArticle: NewsArticle) {
        viewModelScope.launch {
            deleteBookmarkedNewsArticleUseCase(newsArticle = newsArticle)
        }
    }
}

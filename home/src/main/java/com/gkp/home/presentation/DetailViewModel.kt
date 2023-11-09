package com.gkp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.core.domain.BookmarkNewsArticleUseCase
import com.gkp.core.domain.NewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bookmarkNewsArticleUseCase: BookmarkNewsArticleUseCase
) : ViewModel() {

    fun bookmarkNewsArticle(newsArticle: NewsArticle) {
        viewModelScope.launch {
            bookmarkNewsArticleUseCase(newsArticle)
        }
    }
}

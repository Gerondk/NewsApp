package com.gkp.home.presentation

import com.gkp.core.domain.NewsArticle

data class HomeUiState(
    val articlesList: List<NewsArticle> = emptyList(),
    val isLoading: Boolean = false
)

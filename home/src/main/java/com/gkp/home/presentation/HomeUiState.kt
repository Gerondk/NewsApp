package com.gkp.home.presentation

import com.gkp.core.domain.NewsArticle
sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Error(val errorMessage: String? = null) : HomeUiState
    data class Success(
        val articles: List<NewsArticle> = emptyList(),
        val source: String? = null
    ) : HomeUiState {
        val sources = articles.map { it.source.name }.toSet()
        val sourceArticles = articles.filter { it.source.name == source }
    }
}

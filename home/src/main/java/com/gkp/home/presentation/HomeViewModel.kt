package com.gkp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.core.ResourceState
import com.gkp.core.domain.GetBookmarkedArticles
import com.gkp.core.domain.NewsArticle
import com.gkp.home.domain.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

private const val COUNTRY_CODE = "us"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val getBookmarkedArticles: GetBookmarkedArticles
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getTopNews()
    }

    private fun getTopNews() {
        combine(
            getTopHeadlinesUseCase(COUNTRY_CODE),
            getBookmarkedArticles()
        ) { resource, articles ->
            val bookedArticlesTitles = articles.map { it.title }
            when (resource) {
                is ResourceState.Error -> resource
                ResourceState.Loading -> resource
                is ResourceState.Success -> {
                    val articlesWithBookMarkSet = getNewsArticlesWithBookMarkedSet(
                        resource,
                        bookedArticlesTitles
                    )
                    ResourceState.Success(data = articlesWithBookMarkSet)
                }
            }
        }.onEach { resourceState ->
            when (resourceState) {
                is ResourceState.Error -> {
                    _uiState.update {
                        HomeUiState.Error(errorMessage = resourceState.message)
                    }
                }
                ResourceState.Loading -> {
                    _uiState.update { HomeUiState.Loading }
                }
                is ResourceState.Success -> {
                    _uiState.update {
                        HomeUiState.Success(
                            articles = resourceState.data
                                .filterNot { it.urlToImage == null }
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getNewsArticlesWithBookMarkedSet(
        resource: ResourceState.Success<List<NewsArticle>>,
        bookedArticlesTitles: List<String>
    ): MutableList<NewsArticle> {
        val apiArticles = resource.data
        val articlesWithBookMarkSet = mutableListOf<NewsArticle>()
        apiArticles.forEach {
            if (it.title in bookedArticlesTitles) {
                articlesWithBookMarkSet.add(it.copy(isBookMarked = true))
            } else {
                articlesWithBookMarkSet.add(it)
            }
        }
        return articlesWithBookMarkSet
    }

    fun onSelectedSource(source: String) {
        _uiState.update {
            val uiSuccess = it as HomeUiState.Success
            if (it.source == source) {
                uiSuccess.copy(source = null)
            } else {
                uiSuccess.copy(source = source)
            }
        }
    }

    fun retryNewsArticles() {
        getTopNews()
    }
}

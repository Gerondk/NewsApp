package com.gkp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.core.ResourceState
import com.gkp.home.domain.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getTopNews()
    }

    private fun getTopNews() {
        getTopHeadlinesUseCase("us")
            .onEach { resourceState ->
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

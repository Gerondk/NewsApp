package com.gkp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.core.ResourceState
import com.gkp.home.domain.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getTopNews()
    }

    private fun getTopNews() {
        getTopHeadlinesUseCase("us")
            .onEach { resourceState ->
                when (resourceState) {
                    is ResourceState.Error -> {}
                    ResourceState.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is ResourceState.Success -> {
                        _uiState.update {
                            it.copy(
                                articlesList = resourceState.data,
                                isLoading = false
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }
}
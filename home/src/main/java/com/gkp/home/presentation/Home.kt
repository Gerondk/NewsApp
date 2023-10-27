package com.gkp.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gkp.core.domain.NewsArticle
import com.gkp.core.ui.ErrorScreen
import com.gkp.home.presentation.component.NewsArticleList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Home(
    homeUiState: HomeUiState,
    onNavigateToDetail: (NewsArticle) -> Unit,
    onError: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "News") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when (homeUiState) {
                is HomeUiState.Error -> {
                    ErrorScreen(
                        modifier = Modifier.align(Alignment.Center),
                        message = homeUiState.errorMessage ?: "",
                        buttonText = "retry",
                        onError = onError
                    )
                }

                HomeUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is HomeUiState.Success -> {
                    NewsArticleList(
                        articles = homeUiState.articles,
                        onNavigateToDetail = onNavigateToDetail
                    )
                }
            }
        }
    }
}

package com.gkp.home.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gkp.core.domain.NewsArticle
import com.gkp.core.ui.NewsArticleItem
import com.gkp.home.presentation.HomeUiState

@Composable
fun NewsArticleList(
    homeUiState: HomeUiState,
    onNavigateToDetail: (NewsArticle) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(homeUiState.articlesList) { item ->
            NewsArticleItem(
                modifier = Modifier
                    .clickable { onNavigateToDetail(item) },
                item
            )
        }
    }
}
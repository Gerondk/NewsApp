package com.gkp.home.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gkp.core.domain.NewsArticle
import com.gkp.core.ui.NewsArticleItem

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun NewsArticleList(
    articles: List<NewsArticle>,
    onNavigateToDetail: (NewsArticle) -> Unit,
    sources: Set<String>,
    onSelectedSource: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        FlowRow(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            maxItemsInEachRow = 10
        ) {
            sources.forEach { source ->
                Button(
                    onClick = { onSelectedSource(source) }
                ) {
                    Text(text = source)
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        AnimatedVisibility(visible = articles.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(articles) { item ->
                    item.urlToImage?.let {
                        NewsArticleItem(
                            modifier = Modifier
                                .animateItemPlacement(
                                    animationSpec = tween(
                                        easing = LinearOutSlowInEasing,
                                        durationMillis = 500
                                    )
                                )
                                .fillMaxWidth()
                                .clickable { onNavigateToDetail(item) },
                            item
                        )
                    }
                }
            }
        }
    }
}

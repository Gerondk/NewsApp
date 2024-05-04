package com.gkp.home.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gkp.core.domain.NewsArticle
import com.gkp.core.ui.NewsArticleItem
import kotlinx.coroutines.launch

private const val TOP_POSITION = 0

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun NewsArticleList(
    articles: List<NewsArticle>,
    onNavigateToDetail: (NewsArticle) -> Unit,
    sources: Set<String>,
    onSelectedSource: (String) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FlowRow(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            sources.forEach { source ->
                Button(
                    onClick = { onSelectedSource(source) }
                ) {
                    Text(text = source)
                }
            }
        }
        AnimatedVisibility(visible = articles.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                state = lazyListState
            ) {
                items(articles.size) { index ->
                    val item = articles[index]
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
                    if (index == articles.size - 1) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        lazyListState.animateScrollToItem(TOP_POSITION)
                                    }
                                }
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "NO MORE ARTICLES: GO TOP",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}

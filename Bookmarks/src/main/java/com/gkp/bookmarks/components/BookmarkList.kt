package com.gkp.bookmarks.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.gkp.core.domain.NewsArticle
import com.gkp.core.ui.NewsArticleItem

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarkedArticlesList(
    articles: List<NewsArticle>,
    onDeleteBookmark: (NewsArticle) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    LazyColumn(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(articles, key = { it.id }) { item ->
            NewsArticleItem(
                modifier = Modifier.animateItemPlacement(
                    animationSpec = tween(
                        easing = LinearEasing,
                        durationMillis = 500
                    )
                ),
                newsArticle = item,
                showDelete = true,
                onDelete = onDeleteBookmark,
                onReadMore = { uriHandler.openUri(item.url ?: "") }
            )
        }
    }
}

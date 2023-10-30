package com.gkp.bookmarks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.gkp.core.domain.NewsArticle
import com.gkp.core.ui.NewsArticleItem

@Composable
fun BookmarkedArticlesList(
    articles: List<NewsArticle>,
    onDeleteBookmark: (NewsArticle) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(articles, key = { it.id }) { item ->
            NewsArticleItem(
                modifier = Modifier,
                newsArticle = item,
                showDelete = true,
                onDelete = onDeleteBookmark,
                onReadMore = { uriHandler.openUri(item.url ?: "") }
            )
        }
    }
}

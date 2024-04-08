package com.gkp.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gkp.core.domain.NewsArticle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    onBackClicked: () -> Unit,
    newsArticle: NewsArticle,
    onBookmark: (NewsArticle) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = "Article") },
                actions = {
                    if (!newsArticle.isBookMarked) {
                        IconButton(onClick = { onBookmark(newsArticle) }) {
                            Icon(
                                imageVector = Icons.Outlined.BookmarkBorder,
                                contentDescription = "bookmark"
                            )
                        }
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                    IconButton(
                        onClick = {
                            newsArticle.url?.let { uriHandler.openUri(it) }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "bookmark"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())

        ) {
            LazyColumn(contentPadding = PaddingValues(10.dp)) {
                item {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        model = newsArticle.urlToImage,
                        contentScale = ContentScale.Crop,
                        contentDescription = "image"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = newsArticle.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = newsArticle.author ?: "", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = newsArticle.content ?: "", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = newsArticle.publishedDay ?: "", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = newsArticle.source.name, fontSize = 14.sp)
                }
            }
        }
    }
}

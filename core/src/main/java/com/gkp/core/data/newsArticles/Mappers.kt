package com.gkp.core.data.newsArticles

import android.os.Build
import androidx.annotation.RequiresApi
import com.gkp.core.data.newsArticles.local.NewsArticleEntity
import com.gkp.core.domain.NewsArticle

@RequiresApi(Build.VERSION_CODES.O)
fun NewsArticleEntity.toNewsArticle() =
    NewsArticle(
        id = id,
        author = author,
        title = title,
        url = url,
        urlToImage = urlToImage,
        publishedDay = publishedDay,
        content = content,
        source = source
    )

@RequiresApi(Build.VERSION_CODES.O)
fun NewsArticle.toNewsArticleEntity() =
    NewsArticleEntity(
        id = id,
        author = author,
        title = title,
        url = url,
        urlToImage = urlToImage,
        publishedDay = publishedDay,
        content = content,
        source = source
    )

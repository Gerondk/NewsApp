package com.gkp.core.data.newsArticles

import com.gkp.core.data.newsArticles.local.NewsArticleEntity
import com.gkp.core.domain.NewsArticle

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

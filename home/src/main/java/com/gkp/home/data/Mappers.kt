package com.gkp.home.data

import com.gkp.core.domain.NewsArticle

fun Article.mapToNewsArticle() =
    NewsArticle(
        author = author,
        title = title,
        url = url,
        urlToImage = urlToImage,
        publishedDay = publishedAt,
        content = content,
        source = source
    )

fun NewsDto.toArticlesList() = articles.map { it.mapToNewsArticle() }


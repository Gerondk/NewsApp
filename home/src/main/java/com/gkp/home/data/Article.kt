package com.gkp.home.data

import com.gkp.core.data.news_article.model.Source

data class Article(
    val author: String,
    val content: String? = null,
    val description: Any,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String? = null,
)
package com.gkp.home.data

data class NewsDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

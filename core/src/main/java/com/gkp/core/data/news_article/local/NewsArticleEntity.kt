package com.gkp.core.data.news_article.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gkp.core.data.news_article.model.Source

@Entity
data class NewsArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String? = null,
    val title: String,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedDay: String? = null,
    val content: String? = null,
    val source: Source
)

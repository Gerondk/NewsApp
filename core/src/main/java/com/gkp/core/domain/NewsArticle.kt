package com.gkp.core.domain

import android.os.Parcelable
import com.gkp.core.data.news_article.model.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsArticle(
    val id: Int = 0,
    val author: String? = null,
    val title: String,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedDay: String? = null,
    val content: String? = null,
    val source: Source
) : Parcelable

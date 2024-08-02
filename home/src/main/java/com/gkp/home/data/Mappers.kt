package com.gkp.home.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.gkp.core.domain.NewsArticle
import com.gkp.core.formatDateString

@RequiresApi(Build.VERSION_CODES.O)
fun Article.mapToNewsArticle() =
    NewsArticle(
        author = author,
        title = title,
        url = url,
        urlToImage = urlToImage,
        publishedDay = formatDateString(publishedAt),
        content = content,
        source = source
    )

@RequiresApi(Build.VERSION_CODES.O)
fun NewsDto.toArticlesList() = articles.map { it.mapToNewsArticle() }

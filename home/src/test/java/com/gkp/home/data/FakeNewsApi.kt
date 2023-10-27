package com.gkp.home.data

import com.gkp.core.data.news_article.model.Source

internal class FakeNewsApi : NewsApi {

    var isError = false
    val articlesList = (1..6).map {
        Article(
            author = "Article$it",
            content = null,
            description = "",
            publishedAt = "Callie",
            source = Source(id = "Lorissa", name = "Reilly"),
            title = "Rania",
            url = "Nickie",
            urlToImage = null
        )
    }

    override suspend fun getTopHeadlines(country: String, apiKey: String, pageSize: Int): NewsDto {
        if (isError) throw Exception("FakeException")
        return NewsDto(
            articles = articlesList,
            status = "",
            totalResults = 0
        )
    }
}

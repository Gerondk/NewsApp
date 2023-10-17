package com.gkp.core.domain

import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {
    fun getBookmarksNewsArticles(): Flow<List<NewsArticle>>
    suspend fun bookmarkNewsArticle(newsArticle: NewsArticle)
    suspend fun deleteBookmarkedNewsArticles(newsArticle: NewsArticle)
}

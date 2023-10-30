package com.gkp.core.data.news_article

import com.gkp.core.data.news_article.local.NewsArticleDao
import com.gkp.core.di.IoDispatcher
import com.gkp.core.domain.BookmarksRepository
import com.gkp.core.domain.NewsArticle
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BookmarksRepositoryImpl @Inject constructor(
    private val newsArticleDao: NewsArticleDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BookmarksRepository {
    override fun getBookmarksNewsArticles(): Flow<List<NewsArticle>> {
        return newsArticleDao.getAllBookmarkedNewsArticles().map { articles ->
            articles.map { it.toNewsArticle() }
        }.flowOn(dispatcher)
    }

    override suspend fun bookmarkNewsArticle(newsArticle: NewsArticle) {
        withContext(dispatcher) {
            newsArticleDao.insertUpdate(newsArticle.toNewsArticleEntity())
        }
    }

    override suspend fun deleteBookmarkedNewsArticles(newsArticle: NewsArticle) {
        withContext(dispatcher) {
            newsArticleDao.delete(newsArticle.toNewsArticleEntity())
        }
    }
}

package com.gkp.home.data

import com.gkp.core.API_KEY
import com.gkp.core.ResourceState
import com.gkp.core.di.IoDispatcher
import com.gkp.core.domain.NewsArticle
import com.gkp.core.safeApiCall
import com.gkp.home.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : NewsRepository {
    override fun getTopHeadlines(country: String): Flow<ResourceState<List<NewsArticle>>> =
        safeApiCall(
            mapper = { it.toArticlesList() },
            apiCall = { newsApi.getTopHeadlines(country, API_KEY) }
        ).flowOn(dispatcher)

}
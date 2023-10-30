package com.gkp.home.data

import com.gkp.core.ResourceState
import com.gkp.core.di.IoDispatcher
import com.gkp.core.domain.NewsArticle
import com.gkp.core.entities.AppConfig
import com.gkp.core.safeApiCall
import com.gkp.home.domain.NewsRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val appConfig: AppConfig
) : NewsRepository {
    override fun getTopHeadlines(country: String): Flow<ResourceState<List<NewsArticle>>> =
        safeApiCall(
            mapper = { it.toArticlesList() },
            apiCall = { newsApi.getTopHeadlines(country, appConfig.apiKey) }
        ).flowOn(dispatcher)
}

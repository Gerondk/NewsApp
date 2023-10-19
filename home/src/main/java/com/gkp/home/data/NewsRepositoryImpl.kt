package com.gkp.home.data


import com.gkp.core.ResourceState
import com.gkp.core.di.IoDispatcher
import com.gkp.core.domain.NewsArticle
import com.gkp.core.safeApiCall
import com.gkp.home.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @Named("apiKey") private val apiKey :  String
) : NewsRepository {
    override fun getTopHeadlines(country: String): Flow<ResourceState<List<NewsArticle>>> =
        safeApiCall(
            mapper = { it.toArticlesList() },
            apiCall = { newsApi.getTopHeadlines(country,apiKey) }
        ).flowOn(dispatcher)

}
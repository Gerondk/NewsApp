package com.gkp.home.domain

import com.gkp.core.ResourceState
import com.gkp.core.domain.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines(country: String): Flow<ResourceState<List<NewsArticle>>>
}

package com.gkp.home.data

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.gkp.core.ResourceState
import com.gkp.core.entities.AppConfig
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
internal class NewsRepositoryImplTest {
    private lateinit var newsRepositoryImpl: NewsRepositoryImpl
    private val newsApi = FakeNewsApi()

    @BeforeEach
    fun setup() {
        val appConfig = AppConfig(apiKey = "Cady", isDebugMode = true)
        val dispatcher = StandardTestDispatcher()
        newsRepositoryImpl = NewsRepositoryImpl(newsApi, dispatcher, appConfig)
    }

    @Test
    fun `news api call returns a success`() = runTest {
        //action
        val resourcesFlow = newsRepositoryImpl.getTopHeadlines("us")
        resourcesFlow.test {
            assertThat(awaitItem()).isInstanceOf(ResourceState.Loading::class.java)
            val success = awaitItem() as ResourceState.Success
            assertThat(success).isEqualTo(ResourceState.Success(newsApi.articlesList.map { it.mapToNewsArticle() }))
            val first = success.data.first().author
            assertThat(first).isEqualTo("Article1")
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `news api call returns a error when exception is thrown`() = runTest {
        newsApi.isError = true
        //action
        val resourcesFlow = newsRepositoryImpl.getTopHeadlines("us")
        resourcesFlow.test {
            assertThat(awaitItem()).isInstanceOf(ResourceState.Loading::class.java)
            val success = awaitItem() as ResourceState.Error
            assertThat(success).isEqualTo(ResourceState.Error(message = "FakeException"))
            cancelAndIgnoreRemainingEvents()
        }
    }

}
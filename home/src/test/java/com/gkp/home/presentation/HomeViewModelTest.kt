package com.gkp.home.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.gkp.core.ResourceState
import com.gkp.core.data.newsArticles.model.Source
import com.gkp.core.domain.NewsArticle
import com.gkp.home.data.MainCoroutineExtension
import com.gkp.home.domain.GetTopHeadlinesUseCase
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class HomeViewModelTest {
    private val getTopHeadlinesUseCase = mockk<GetTopHeadlinesUseCase>()
    private lateinit var homeViewModel: HomeViewModel

    @BeforeEach
    fun setup() {
        clearMocks(getTopHeadlinesUseCase)
    }

    @Test
    fun `only loading state is triggered`() = runTest {
        every { getTopHeadlinesUseCase(any()) } returns flowOf()
        homeViewModel = HomeViewModel(getTopHeadlinesUseCase)
        homeViewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(HomeUiState.Loading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun ` loading and success states are triggered`() = runTest {
        val source = Source(id = "", name = "")
        val firstArticle = NewsArticle(id = 1, title = "title", source = source)
        val secondArticle = NewsArticle(id = 2, title = "title", source = source)
        val articles = listOf(firstArticle, secondArticle)
        every { getTopHeadlinesUseCase(any()) } returns flowOf(ResourceState.Success(articles))
        homeViewModel = HomeViewModel(getTopHeadlinesUseCase)

        homeViewModel.uiState.test {
            val loadingEmission = awaitItem()

            val successEmission = awaitItem()
            assertThat(successEmission).isInstanceOf(HomeUiState.Success::class.java)
            val success = successEmission as HomeUiState.Success
            assertThat(success.articles).contains(firstArticle)
            assertThat(success.articles).contains(secondArticle)
        }
    }

    @Test
    fun `loading and error states are triggered`() = runTest {
        val errorMessage = "Network error"
        every { getTopHeadlinesUseCase(any()) } returns
                flowOf(
                    ResourceState.Error(message = errorMessage)
                )

        homeViewModel = HomeViewModel(getTopHeadlinesUseCase)

        homeViewModel.uiState.test {
            val loadingEmission = awaitItem()

            val errorEmission = awaitItem()
            assertThat(errorEmission).isInstanceOf(HomeUiState.Error::class.java)
            val error = errorEmission as HomeUiState.Error
            assertThat(error.errorMessage).isEqualTo(errorMessage)
        }
    }

    @Test
    fun `retry calls invokes getTopHeadlines`() {
        every { getTopHeadlinesUseCase(any()) } returns flowOf()
        homeViewModel = HomeViewModel(getTopHeadlinesUseCase)

        homeViewModel.retryNewsArticles()

        verify(atLeast = 2) { getTopHeadlinesUseCase(any()) }
    }
}

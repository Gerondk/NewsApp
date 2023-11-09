package com.gkp.home.presentation

import com.gkp.core.domain.BookmarkNewsArticleUseCase
import com.gkp.core.domain.NewsArticle
import com.gkp.home.data.MainCoroutineExtension
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel
    private val bookmarkNewsArticleUseCase = mockk<BookmarkNewsArticleUseCase>()

    @BeforeEach
    fun setup() {
        clearMocks(bookmarkNewsArticleUseCase)
    }

    @Test
    fun `bookmarkNewsArticleUseCase is called  when bookmarkNewsArticle is executed`() = runTest {
        val newsArticle = mockk<NewsArticle>()
        coEvery { bookmarkNewsArticleUseCase(article = newsArticle) } returns Unit
        detailViewModel = DetailViewModel(bookmarkNewsArticleUseCase)

        detailViewModel.bookmarkNewsArticle(newsArticle)
        advanceUntilIdle()

        coVerify(exactly = 1) { bookmarkNewsArticleUseCase(any()) }
    }
}

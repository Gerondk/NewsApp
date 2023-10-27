package com.gkp.core.domain

import javax.inject.Inject

class DeleteBookmarkedNewsArticleUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) {
    suspend operator fun invoke(newsArticle: NewsArticle) =
        bookmarksRepository.deleteBookmarkedNewsArticles(newsArticle)
}

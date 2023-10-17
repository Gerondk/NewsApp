package com.gkp.core.domain

import javax.inject.Inject

class BookmarkNewsArticleUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) {
    suspend operator fun invoke(article: NewsArticle) =
        bookmarksRepository.bookmarkNewsArticle(article)

}
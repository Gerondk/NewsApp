package com.gkp.core.domain

import javax.inject.Inject

class GetBookmarkedArticles @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) {
    operator fun invoke() = bookmarksRepository.getBookmarksNewsArticles()
}
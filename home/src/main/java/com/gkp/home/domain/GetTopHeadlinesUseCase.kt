package com.gkp.home.domain

import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(country: String) = repository.getTopHeadlines(country)
}

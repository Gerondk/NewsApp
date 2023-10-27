package com.gkp.core.di

import com.gkp.core.data.news_article.BookmarksRepositoryImpl
import com.gkp.core.domain.BookmarksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BookmarksModule {
    @Singleton
    @Binds
    abstract fun bindsBookmarkRepository(impl: BookmarksRepositoryImpl): BookmarksRepository
}

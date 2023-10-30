package com.gkp.core.di

import android.content.Context
import androidx.room.Room
import com.gkp.core.data.newsArticles.local.NewsArticleDatabase
import com.gkp.core.data.newsArticles.local.NewsArticlesTypConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun providesNewsArticlesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            NewsArticleDatabase::class.java,
            NewsArticleDatabase.APP_DB_NAME
        ).addTypeConverter(NewsArticlesTypConverters())
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesNewsArticlesDao(newsDatabase: NewsArticleDatabase) =
        newsDatabase.newsArticleDao()
}

package com.gkp.core.data.news_article.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NewsArticleEntity::class], version = 1)
@TypeConverters(NewsArticlesTypConverters::class)
abstract class NewsArticleDatabase : RoomDatabase() {
    abstract fun newsArticleDao(): NewsArticleDao

    companion object {
        const val APP_DB_NAME = "news_articles_database"
    }
}

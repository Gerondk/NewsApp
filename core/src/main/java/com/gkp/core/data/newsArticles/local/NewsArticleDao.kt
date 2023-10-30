package com.gkp.core.data.newsArticles.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {
    @Query("SELECT * FROM NewsArticleEntity")
    fun getAllBookmarkedNewsArticles(): Flow<List<NewsArticleEntity>>

    @Upsert
    suspend fun insertUpdate(newsArticleEntity: NewsArticleEntity)

    @Delete
    suspend fun delete(newsArticleEntity: NewsArticleEntity)
}

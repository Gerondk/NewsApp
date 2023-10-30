package com.gkp.core.data.newsArticles.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gkp.core.data.newsArticles.model.Source

@ProvidedTypeConverter
class NewsArticlesTypConverters {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(sourceString: String): Source {
        val sourceEntity = sourceString.split(",")
        return Source(id = sourceEntity.first(), name = sourceEntity.last())
    }
}

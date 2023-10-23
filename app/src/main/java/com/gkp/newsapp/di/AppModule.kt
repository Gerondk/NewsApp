package com.gkp.newsapp.di

import com.gkp.core.entities.AppConfig
import com.gkp.newsapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAppConfig() = AppConfig(
        apiKey = BuildConfig.newsApiKey,
        isDebugMode = BuildConfig.DEBUG
    )
}
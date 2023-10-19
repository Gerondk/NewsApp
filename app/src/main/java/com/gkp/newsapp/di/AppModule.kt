package com.gkp.newsapp.di

import com.gkp.newsapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Named("apiKey")
    @Provides
    fun providesApiKey() = BuildConfig.newsApiKey
}
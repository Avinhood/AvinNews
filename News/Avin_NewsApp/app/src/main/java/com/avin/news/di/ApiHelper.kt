package com.avin.news.di

import com.avin.news.backend.api.NewsApi
import com.avin.news.backend.repository.NewsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiHelper {

    @Provides
    @Singleton
    internal fun getNewsApi(retroFit: Retrofit): NewsApi {
        return retroFit.create(NewsApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideNewsRepository():NewsRepository{
        return NewsRepository()
    }

}
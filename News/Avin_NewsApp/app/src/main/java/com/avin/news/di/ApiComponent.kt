package com.avin.news.di

import com.avin.libnetwrok.Network
import com.avin.news.backend.api.NewsApi
import com.avin.news.backend.repository.NewsRepository
import com.avin.news.viewmodel.NewsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiHelper::class, AppModule::class, DBModule::class, Network::class])
interface ApiComponent {

    val newsApi: NewsApi

    fun inject(repo: NewsRepository)
    fun inject(newsVM: NewsViewModel)
}
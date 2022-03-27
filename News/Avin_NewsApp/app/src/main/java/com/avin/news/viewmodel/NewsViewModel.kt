package com.avin.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avin.news.Appcontroller
import com.avin.news.backend.repository.NewsRepository
import com.avin.news.model.BaseModel
import com.avin.news.model.NewsData
import java.util.*
import javax.inject.Inject


class NewsViewModel : ViewModel() {

    var newsLiveData: MutableLiveData<BaseModel>

    init {
        this.newsLiveData = MutableLiveData()
        Appcontroller.app.mApiComponent.inject(this)
    }

    @Inject
    lateinit var mRepository: NewsRepository

    fun getNewsList(): MutableLiveData<BaseModel> {
        newsLiveData = mRepository.fetchNewsList()
        return newsLiveData
    }

}
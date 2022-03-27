package com.avin.news.backend.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.avin.news.Appcontroller
import com.avin.news.backend.api.NewsApi
import com.avin.news.di.ApiComponent
import com.avin.news.model.BaseModel
import com.avin.news.model.NewsData
import com.avin.news.room.NewsDao
import com.avin.news.util.Constants
import com.avin.news.util.Utils
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class NewsRepository {

    val newsListLiveData: MutableLiveData<BaseModel> = MutableLiveData()

    init {
        val apiComponent: ApiComponent = Appcontroller.app.mApiComponent
        apiComponent.inject(this)
    }

    @Inject
    lateinit var newsApi: NewsApi

    @Inject
    lateinit var newsDao: NewsDao

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun fetchNewsList(): MutableLiveData<BaseModel> {

        // Fetch offline news
        fetchNewsOffline()

        if (!Utils.isNetworkAvailable()) {
            Toast.makeText(
                Appcontroller.app.applicationContext,
                "Internet not connected",
                Toast.LENGTH_LONG
            ).show()
        }

        val postListInfo: Single<BaseModel> =
            newsApi.getPaymentTypes("US", Constants.NEWS_API_KEY, 50)

        compositeDisposable.add(postListInfo
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                if (response.status == "ok") {
                    // Insert news in DB
                    insertNews(response?.articles)
                    //newsListLiveData.postValue(response.body());
                } else {
//                  val errorData = BaseModel("error", "error loading list", arrayListOf<NewsData>())
//                  newsListLiveData.postValue(errorData)
                    Toast.makeText(
                        Appcontroller.app.applicationContext,
                        "Error loading data, Try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }, { error ->
                Toast.makeText(
                    Appcontroller.app.applicationContext,
                    "Error loading data, Try again later",
                    Toast.LENGTH_LONG
                ).show()
                println(error)
            })
        )

        return newsListLiveData
    }

    // Live data triggered when all records from DB loaded
    private fun fetchNewsOffline() {

        compositeDisposable.add(newsDao.getNews()
            .observeOn(Schedulers.io()).subscribeOn(Schedulers.newThread())
            .subscribe({ result ->
                val newsArraylist = ArrayList<NewsData>()
                newsArraylist.addAll(result)
                val dbData = BaseModel("ok", "", newsArraylist)
                newsListLiveData.postValue(dbData)
            }, { error: Throwable -> println(error) }
            ));


    }

    fun insertNews(newsList: ArrayList<NewsData>?) {

        var needsUpdate = false
        newsList?.stream()?.map { item ->
            {
                val inserted = newsDao.insertNews(item)
                if (inserted == -1L) {
                    val updated = newsDao.insertNews(item)
                    if (updated > 0) {
                        needsUpdate = true
                    }
                } else {
                    needsUpdate = true
                }
            }
        }

        if (needsUpdate)
            fetchNewsOffline()


    }


}
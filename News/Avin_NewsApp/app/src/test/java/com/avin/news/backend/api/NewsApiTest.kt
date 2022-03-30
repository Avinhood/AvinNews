package com.avin.news.backend.api

import com.avin.libnetwrok.Network
import com.avin.news.BaseServiceTest
import com.avin.news.NewsSampleData
import com.avin.news.di.ApiHelper
import com.avin.news.model.BaseModel
import com.avin.news.parse
import com.avin.news.util.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@RunWith(JUnit4::class)
class NewsApiTest : BaseServiceTest() {
    private lateinit var baseModel: BaseModel

    private lateinit var apiHelper : ApiHelper
    private lateinit var retrofit: Retrofit

    companion object{
        val response = parse(NewsSampleData.newsResponse, BaseModel::class.java)
    }

    @Before
    fun setUp(){
        MockKAnnotations.init(this, true, true, true)

        baseModel = BaseModel(response.status, response.message, response.articles)

        apiHelper = ApiHelper()
        retrofit = Retrofit.Builder().baseUrl(Network.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Network.provideGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    }

    @Test
    fun `test is parsing response success`(){
        Assert.assertNotNull(response)
    }

    @Test
    fun `test get country headlines not null`(){
        var resp = apiHelper.getNewsApi(retrofit).getCountryHeadlines("US", Constants.NEWS_API_KEY, 50)
        Assert.assertNotNull(resp)
    }

    @Test
    fun `test get country headlines response ok`(){
        var resp = apiHelper.getNewsApi(retrofit).getCountryHeadlines("US", Constants.NEWS_API_KEY, 50)
        Assert.assertEquals(resp.blockingGet().status,"ok")
    }

    @Test
    fun `test get country headlines response data`(){
        var resp = apiHelper.getNewsApi(retrofit).getCountryHeadlines("US", Constants.NEWS_API_KEY, 50)
        var data = resp.blockingGet().articles[0]
        Assert.assertNotNull(data)

    }
}
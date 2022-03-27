package com.avin.news.backend.api

import com.avin.news.model.BaseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getPaymentTypes(@Query("country") country: String, @Query("apiKey") apiKey: String, @Query("pageSize") page: Int): Single<BaseModel>

}
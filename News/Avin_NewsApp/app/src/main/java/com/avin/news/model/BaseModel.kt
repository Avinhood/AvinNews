package com.avin.news.model

data class BaseModel(
    val status: String?,
    val message: String?,
    val articles: ArrayList<NewsData>
)

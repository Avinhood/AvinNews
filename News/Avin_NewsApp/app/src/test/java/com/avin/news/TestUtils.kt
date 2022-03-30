package com.avin.news

import com.google.gson.GsonBuilder


fun <T> parse(json:String , klass: Class<T>): T {
    return GsonBuilder().create().fromJson(json, klass)
}
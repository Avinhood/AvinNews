package com.avin.news

import android.app.Application
import com.avin.libnetwrok.Network
import com.avin.news.di.*

class Appcontroller : Application()
{

    lateinit var mApiComponent:ApiComponent

    override fun onCreate() {
        super.onCreate()
        app = this

        mApiComponent = DaggerApiComponent.builder()
            .appModule(AppModule(this))
            .apiHelper(ApiHelper())
            .dBModule(DBModule(this))
            .network(Network)
            .build()

    }

    companion object {
        lateinit var app: Appcontroller
            private set
    }

}
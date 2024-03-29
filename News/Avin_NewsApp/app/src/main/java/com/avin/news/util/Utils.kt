package com.avin.news.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.avin.news.Appcontroller


class Utils{
    companion object{
        fun isNetworkAvailable(): Boolean {
            val connectivityManager = Appcontroller.app.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }
    }

}


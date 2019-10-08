package com.example.testmvvmapp.model.network

import android.content.Context
import android.net.ConnectivityManager

class NetManager(private var context: Context)
{
    private var status: Boolean? = false

    val isConnectionToInternet: Boolean?
        get() {
            val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = conManager.activeNetworkInfo

            return netInfo != null && netInfo.isConnected
        }
}
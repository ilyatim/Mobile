package com.example.translator.data.netModule

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetModule {
    val retrofit: Retrofit
        get() {
            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
            return Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://translate.yandex.net/api/v1.5/tr.json")
                .build()
        }
}
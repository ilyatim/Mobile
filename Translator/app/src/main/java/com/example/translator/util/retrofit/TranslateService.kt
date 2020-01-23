package com.example.translator.util.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.translator.data.Result

interface TranslateService {

    @GET("/translate/")
    fun translate(@Query("key") key: String, @Query("text") text: String, @Query("lang") lang: String): Single<Result>

}
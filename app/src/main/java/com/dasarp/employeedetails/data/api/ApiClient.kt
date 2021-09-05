package com.dasarp.employeedetails.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    val instance : ApiService= Retrofit.Builder().run {
        baseUrl(Url.BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
        client(createHttpClient())
        build()
    }.create(ApiService::class.java)

    private fun createHttpClient() : OkHttpClient{
        val interceptor = Interceptor {
            val originalPath = it.request()
            val requestBuilder = originalPath.newBuilder()
            it.proceed(requestBuilder.build())
        }
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(interceptor)
            .readTimeout(Url.REQUEST_TIMEOUT_DURATION,TimeUnit.SECONDS)
            .connectTimeout(Url.REQUEST_TIMEOUT_DURATION,TimeUnit.SECONDS)
            .writeTimeout(Url.REQUEST_TIMEOUT_DURATION,TimeUnit.SECONDS)
            .build()
    }

}
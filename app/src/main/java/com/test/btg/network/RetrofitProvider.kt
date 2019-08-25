package com.test.btg.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofit by lazy {
    provideRetrofit()
}

private fun provideRetrofit(url: String = "https://api.themoviedb.org/3/"): Retrofit {
    return Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .build()
}
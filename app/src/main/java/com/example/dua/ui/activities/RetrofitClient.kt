package com.example.dua.ui.activities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://api.aladhan.com/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val prayerTimesApiService: PrayerTimesApiService by lazy {
        retrofit.create(PrayerTimesApiService::class.java)
    }
}

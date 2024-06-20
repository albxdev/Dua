package com.example.dua.ui.activities

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PrayerTimesApiService {

    @GET("calendarByCity/{year}/{month}")
    fun getPrayerTimesByCity(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("method") method: Int = 3,
    ): Call<CityCoordinatesResponse>
}

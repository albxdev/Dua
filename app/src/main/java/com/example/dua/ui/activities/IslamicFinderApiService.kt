// IslamicFinderApiService.kt
package com.example.dua.ui.activities

import com.example.dua.models.PrayerTimingsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IslamicFinderApiService {

    @GET("hijriCalendarByCity/{year}/{month}")
    fun getPrayerTimings(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("method") method: Int
    ): Call<PrayerTimingsResponse>

    companion object {
        private const val BASE_URL = "http://api.aladhan.com/v1/methods"

        fun create(): IslamicFinderApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(IslamicFinderApiService::class.java)
        }
    }
}
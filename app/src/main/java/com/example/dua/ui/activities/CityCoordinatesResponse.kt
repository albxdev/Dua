package com.example.dua.ui.activities

data class CityCoordinatesResponse(
    val data: List<PrayerData>
)

data class PrayerData(
    val date: DateInfo,
    val timings: PrayerTimings
)

data class DateInfo(
    val hijri: HijriDate,
    val gregorian: GregorianDate
)

data class HijriDate(
    val day: String,
    val month: HijriMonth,
    val year: Int
)

data class HijriMonth(
    val number: Int,
    val text: String
)

data class GregorianDate(
    val date: String
)

data class PrayerTimings(
    val Fajr: String,
    val Sunrise: String,
    val Dhuhr: String,
    val Asr: String,
    val Maghrib: String,
    val Isha: String
)

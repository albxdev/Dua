package com.example.dua.ui.activities

data class PrayerTimingsResponse(
    val code: Int,
    val status: String,
    val data: List<PrayerTimingData>
)

data class PrayerTimingData(
    val timings: Timings,
    val date: DateInfo
)

data class DateInfo(
    val readable: String,
    val timestamp: String,
    val gregorian: GregorianDate,
    val hijri: HijriDate
)

data class GregorianDate(
    val date: String,
    val month: GregorianMonth,
    val year: String
)

data class GregorianMonth(
    val en: String
)

data class HijriDate(
    val date: String,
    val day: String,
    val month: HijriMonth,
    val year: String
)

data class HijriMonth(
    val en: String
)

data class Timings(
    val Fajr: String,
    val Sunrise: String,
    val Dhuhr: String,
    val Asr: String,
    val Maghrib: String,
    val Isha: String
)

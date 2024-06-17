package com.example.dua.models


data class Timings(
    val Fajr: String,
    val Sunrise: String,
    val Dhuhr: String,
    val Asr: String,
    val Sunset: String,
    val Maghrib: String,
    val Isha: String,
    val Imsak: String,
    val Midnight: String
)

data class GregorianDate(
    val date: String,
    val format: String,
    val day: String,
    val weekday: Weekday,
    val month: Month,
    val year: String,
    val designation: Designation
)

data class HijriDate(
    val date: String,
    val format: String,
    val day: String,
    val weekday: Weekday,
    val month: Month,
    val year: String,
    val designation: Designation,
    val holidays: List<String>
)

data class Weekday(
    val en: String,
    val ar: String? = null
)

data class Month(
    val number: Int,
    val en: String,
    val ar: String? = null
)

data class Designation(
    val abbreviated: String,
    val expanded: String
)

data class Date(
    val readable: String,
    val timestamp: String,
    val gregorian: GregorianDate,
    val hijri: HijriDate
)

data class Method(
    val id: Int,
    val name: String,
    val params: Params
)

data class Params(
    val Fajr: Int,
    val Isha: Int
)

data class Meta(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val method: Method,
    val latitudeAdjustmentMethod: String,
    val midnightMode: String,
    val school: String,
    val offset: Map<String, Int>
)

data class PrayerTimingsResponse(
    val code: Int,
    val status: String,
    val data: List<DayData>
)

data class DayData(
    val timings: Timings,
    val date: Date,
    val meta: Meta
)

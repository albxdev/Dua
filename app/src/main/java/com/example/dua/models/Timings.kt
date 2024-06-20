package com.example.dua.models


// Define un data class para representar la estructura de las fechas Hijri
data class HijriDate(
    val day: String,
    val month: HijriMonth,
    val year: String
)

// Define un data class para representar el mes Hijri
data class HijriMonth(
    val number: Int,
    val en: String?,
    val ar: String?
)

// Define un data class para representar la fecha gregoriana
data class GregorianDate(
    val date: String,
    val format: String,
    val day: String,
    val weekday: Weekday,
    val month: Month,
    val year: String,
    val designation: Designation
)

// Define un data class para representar la fecha
data class Weekday(
    val en: String
)

// Define un data class para representar el mes
data class Month(
    val number: Int,
    val en: String
)

// Define un data class para representar la designación
data class Designation(
    val abbreviated: String,
    val expanded: String
)

// Define un data class para representar la fecha en la respuesta de la API
data class PrayerDate(
    val readable: String,
    val timestamp: String,
    val gregorian: GregorianDate,
    val hijri: HijriDate
)

// Define un data class para representar los tiempos de oración en la respuesta de la API
data class PrayerTiming(
    val timings: Map<String, String>,
    val date: PrayerDate,
    val meta: MetaInfo
)

// Define un data class para representar la información de meta en la respuesta de la API
data class MetaInfo(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val method: Method,
    val latitudeAdjustmentMethod: String,
    val midnightMode: String,
    val school: String,
    val offset: Map<String, Int>
)

// Define un data class para representar el método en la meta información
data class Method(
    val id: Int,
    val name: String,
    val params: MethodParams,
    val location: MethodLocation
)

// Define un data class para representar los parámetros del método
data class MethodParams(
    val Fajr: Int,
    val Isha: Int
)

// Define un data class para representar la ubicación del método
data class MethodLocation(
    val latitude: Double,
    val longitude: Double
)

// Define un data class para representar la respuesta de los tiempos de oración desde la API
data class PrayerTimesResponse(
    val code: Int,
    val status: String,
    val data: List<PrayerTiming>
)

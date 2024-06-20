    package com.example.dua.models


    object CityCoordinatesContract{
        const val TABLE_NAME = "CityCoordinates"
        const val COLUMN_ID = "id"
        const val COLUMN_CITY = "city"
        const val COLUMN_COUNTRY = "country"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_LONGITUDE = "longitude"
        const val COLUMN_TIMEZONE = "timezone"
    }




    data class CityCoordinates(
        val id: Int,
        val city: String,
        val country: String,
        val latitude: String,
        val longitude: String,
        val timezone: String
    )


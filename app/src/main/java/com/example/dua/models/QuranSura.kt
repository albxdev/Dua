package com.example.dua.models

object QuranSuraContract {
    const val TABLE_NAME = "suras"
    const val COLUMN_ID = "id"
    const val COLUMN_NUMBER = "number"
    const val COLUMN_ARABIC = "arabic"
    const val COLUMN_TRANSLITERATION = "transliteration"
    const val COLUMN_SPANISH = "spanish"
    const val COLUMN_TAFSIR = "tafsir"
    const val COLUMN_REVELATION = "revelation"
    const val COLUMN_NAME = "name"
    const val COLUMN_JUZ = "juz"
}


data class QuranSura(
    val id: Int,
    val number: Int,
    val arabic: String,
    val transliteration: String,
    val spanish: String,
    val tafsir: String,
    val revelation: String,
    val name: String,
    val juz: Int

)
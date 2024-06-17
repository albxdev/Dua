package com.example.dua.models


object AleyaContract {
    const val TABLE_NAME = "aleyas"
    const val COLUMN_ID = "id"
    const val COLUMN_SURA_ID = "suraId"
    const val COLUMN_NUMBER = "number"
    const val COLUMN_ARABIC_TEXT = "arabicText"
    const val COLUMN_TRANSLITERATION = "transliteration"
    const val COLUMN_TRANSLATION = "translation"
    const val COLUMN_FOOTNOTE = "footnote"
}

data class Aleya(
    val id: Int,
    val suraId: Int,
    val number: Int,
    val arabicText: String,
    val transliteration: String,
    val translation: String,
    val footnote: String?
)

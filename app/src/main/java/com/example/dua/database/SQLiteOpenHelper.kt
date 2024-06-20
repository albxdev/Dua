package com.example.dua.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.dua.models.Aleya
import com.example.dua.models.AleyaContract
import com.example.dua.models.CityCoordinates
import com.example.dua.models.CityCoordinatesContract
import com.example.dua.models.QuranSura
import com.example.dua.models.QuranSuraContract

class QuranDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ALEYA_TABLE)
        db.execSQL(SQL_CREATE_QURAN_SURA_TABLE)
        db.execSQL(SQL_CREATE_CITY_COORDINATES_TABLE)
        insertAlFatiha(db)
        insertCityCoordinates(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE ${QuranSuraContract.TABLE_NAME} ADD COLUMN ${QuranSuraContract.COLUMN_REVELATION} TEXT")
        }
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE ${QuranSuraContract.TABLE_NAME} ADD COLUMN ${QuranSuraContract.COLUMN_JUZ} INTEGER")
        }
        // Aquí se eliminan las tablas antiguas y se crean de nuevo
        db.execSQL(SQL_DELETE_ALEYA_TABLE)
        db.execSQL(SQL_DELETE_QURAN_SURA_TABLE)
        db.execSQL(SQL_DELETE_CITY_COORDINATES_TABLE)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 4
        private const val DATABASE_NAME = "QuranDatabase.db"

        private const val SQL_CREATE_ALEYA_TABLE = "CREATE TABLE ${AleyaContract.TABLE_NAME} (" +
                "${AleyaContract.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${AleyaContract.COLUMN_SURA_ID} INTEGER," +
                "${AleyaContract.COLUMN_NUMBER} INTEGER," +
                "${AleyaContract.COLUMN_ARABIC_TEXT} TEXT," +
                "${AleyaContract.COLUMN_TRANSLITERATION} TEXT," +
                "${AleyaContract.COLUMN_TRANSLATION} TEXT," +
                "${AleyaContract.COLUMN_FOOTNOTE} TEXT)"

        private const val SQL_CREATE_QURAN_SURA_TABLE = "CREATE TABLE ${QuranSuraContract.TABLE_NAME} (" +
                "${QuranSuraContract.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${QuranSuraContract.COLUMN_NUMBER} INTEGER," +
                "${QuranSuraContract.COLUMN_ARABIC} TEXT," +
                "${QuranSuraContract.COLUMN_TRANSLITERATION} TEXT," +
                "${QuranSuraContract.COLUMN_SPANISH} TEXT," +
                "${QuranSuraContract.COLUMN_TAFSIR} TEXT," +
                "${QuranSuraContract.COLUMN_REVELATION} TEXT," +
                "${QuranSuraContract.COLUMN_NAME} TEXT," +
                "${QuranSuraContract.COLUMN_JUZ} INTEGER)"

        private const val SQL_CREATE_CITY_COORDINATES_TABLE = "CREATE TABLE ${CityCoordinatesContract.TABLE_NAME} (" +
                "${CityCoordinatesContract.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${CityCoordinatesContract.COLUMN_CITY} TEXT," +
                "${CityCoordinatesContract.COLUMN_COUNTRY} TEXT," +
                "${CityCoordinatesContract.COLUMN_LATITUDE} TEXT," +
                "${CityCoordinatesContract.COLUMN_LONGITUDE} TEXT," +
                "${CityCoordinatesContract.COLUMN_TIMEZONE} TEXT)"

        private const val SQL_DELETE_ALEYA_TABLE = "DROP TABLE IF EXISTS ${AleyaContract.TABLE_NAME}"
        private const val SQL_DELETE_QURAN_SURA_TABLE = "DROP TABLE IF EXISTS ${QuranSuraContract.TABLE_NAME}"
        private const val SQL_DELETE_CITY_COORDINATES_TABLE = "DROP TABLE IF EXISTS ${CityCoordinatesContract.TABLE_NAME}"

        private fun insertCityCoordinates(db: SQLiteDatabase) {
            val cityCoordinates = listOf(
                CityCoordinates(1, "Barranquilla", "Colombia", "11.0041072", "-74.8069813", "America/Bogota"),
                CityCoordinates(2, "Medellín", "Colombia", "6.244203", "-75.5812119", "America/Bogota"),
                CityCoordinates(3, "Bucaramanga", "Colombia", "7.119349", "-73.1227416", "America/Bogota"),
                CityCoordinates(4, "Bogotá", "Colombia", "4.7109886", "-74.072092", "America/Bogota")
                // Puedes agregar más ciudades según sea necesario
            )

            cityCoordinates.forEach { city ->
                val values = ContentValues().apply {
                    put(CityCoordinatesContract.COLUMN_CITY, city.city)
                    put(CityCoordinatesContract.COLUMN_COUNTRY, city.country)
                    put(CityCoordinatesContract.COLUMN_LATITUDE, city.latitude)
                    put(CityCoordinatesContract.COLUMN_LONGITUDE, city.longitude)
                    put(CityCoordinatesContract.COLUMN_TIMEZONE, city.timezone)
                }

                // Insertar los valores en la tabla CityCoordinates
                val newRowId = db.insert(CityCoordinatesContract.TABLE_NAME, null, values)
                if (newRowId == -1L) {
                    Log.e("Database", "Error al insertar datos en CityCoordinates")
                } else {
                    Log.d("Database", "Insertado en CityCoordinates, ID: $newRowId")
                }
            }
        }

        private fun insertAlFatiha(db: SQLiteDatabase) {
            val suraValues = ContentValues().apply {
                put(QuranSuraContract.COLUMN_NUMBER, 1)
                put(QuranSuraContract.COLUMN_ARABIC, "الفاتحة")
                put(QuranSuraContract.COLUMN_TRANSLITERATION, "Al-Fatiha")
                put(QuranSuraContract.COLUMN_SPANISH, "La Apertura")
                put(QuranSuraContract.COLUMN_TAFSIR, "Tafsir de Al-Fatiha...")
                put(QuranSuraContract.COLUMN_REVELATION, "La Meca")
                put(QuranSuraContract.COLUMN_NAME, "Al-Fatiha")
                put(QuranSuraContract.COLUMN_JUZ, 1)
            }

            val suraId = db.insert(QuranSuraContract.TABLE_NAME, null, suraValues)

            val aleyas = listOf(
                Triple("بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِِ", "Bismi Allāhi Ar-Raḥmāni Ar-Raḥīm", "En el nombre de Allah, el Compasivo, el Misericordioso"),
                Triple("الْحَمْدُ للّهِ رَبِّ الْعَالَمِينَ", "Al-Ḥamdu Lillāhi Rabbi Al-‘Ālamīn", "Todas las alabanzas son para Allah, Señor de todo cuanto existe"),
                Triple("الرَّحْمـنِ الرَّحِيمِ", "Ar-Raḥmāni Ar-Raḥīm", "El Compasivo, el Misericordioso"),
                Triple("مَالِكِ يَوْمِ الدِّينِ", "Māliki Yawmi Ad-Dīn", "Soberano absoluto del Día del Juicio Final"),
                Triple("إِيَّاكَ نَعْبُدُ وإِيَّاكَ نَسْتَعِينُ", "Iyyāka Na‘budu Wa Iyyāka Nasta‘īn", "Solo a Ti te adoramos y solo de Ti imploramos ayuda"),
                Triple("اهدِنَــــا الصِّرَاطَ المُستَقِيمَ", "Ihdinā Aṣ-Ṣirāṭ Al-Mustaqīm", "¡Guíanos por el camino recto!"),
                Triple("صِرَاطَ الَّذِينَ أَنعَمتَ عَلَيهِمْ غَيرِ المَغضُوبِ عَلَيهِمْ وَلاَ الضَّالِّينَ", "Ṣirāṭ Al-Laḏīna 'An'amta 'Alayhim Ġayri Al-Maġḍūbi 'Alayhim Wa Lā Aḍ-Ḍāllīn", "El camino de los que has colmado con Tus favores, no el de los que han caído en Tu ira, ni el de los que se extraviaron")
            )

            aleyas.forEachIndexed { index, (arabicText, transliteration, spanishTranslation) ->
                val aleyaValues = ContentValues().apply {
                    put(AleyaContract.COLUMN_SURA_ID, suraId)
                    put(AleyaContract.COLUMN_NUMBER, index + 1)
                    put(AleyaContract.COLUMN_ARABIC_TEXT, arabicText)
                    put(AleyaContract.COLUMN_TRANSLITERATION, transliteration)
                    put(AleyaContract.COLUMN_TRANSLATION, spanishTranslation)
                    put(AleyaContract.COLUMN_FOOTNOTE, "Pie de página de la aleya ${index + 1}")
                }
                db.insert(AleyaContract.TABLE_NAME, null, aleyaValues)
            }
        }
    }
}

fun getQuranSuraByNumber(db: SQLiteDatabase, suraNumber: Int): QuranSura? {
    val cursor = db.query(
        QuranSuraContract.TABLE_NAME,
        null,
        "${QuranSuraContract.COLUMN_NUMBER} = ?",
        arrayOf(suraNumber.toString()),
        null,
        null,
        null
    )

    var sura: QuranSura? = null

    cursor?.use {
        if (it.moveToFirst()) {
            sura = QuranSura(
                it.getInt(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_ID)),
                it.getInt(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_NUMBER)),
                it.getString(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_ARABIC)),
                it.getString(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_TRANSLITERATION)),
                it.getString(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_SPANISH)),
                it.getString(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_TAFSIR)),
                it.getString(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_REVELATION)),
                it.getString(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_NAME)),
                it.getInt(it.getColumnIndexOrThrow(QuranSuraContract.COLUMN_JUZ))
            )
            Log.d("Database", "Sura found: $sura")
        } else {
            Log.d("Database", "No sura found with number: $suraNumber")
        }
    }
    cursor?.close()
    return sura
}

fun getAyasBySuraNumber(db: SQLiteDatabase, suraNumber: Int): List<Aleya> {
    val cursor = db.query(
        AleyaContract.TABLE_NAME,
        null,
        "${AleyaContract.COLUMN_SURA_ID} = ?",
        arrayOf(suraNumber.toString()),
        null,
        null,
        null
    )

    val ayas = mutableListOf<Aleya>()

    cursor?.use {
        while (it.moveToNext()) {
            val aleya = Aleya(
                it.getInt(it.getColumnIndexOrThrow(AleyaContract.COLUMN_ID)),
                it.getInt(it.getColumnIndexOrThrow(AleyaContract.COLUMN_SURA_ID)),
                it.getInt(it.getColumnIndexOrThrow(AleyaContract.COLUMN_NUMBER)),
                it.getString(it.getColumnIndexOrThrow(AleyaContract.COLUMN_ARABIC_TEXT)),
                it.getString(it.getColumnIndexOrThrow(AleyaContract.COLUMN_TRANSLITERATION)),
                it.getString(it.getColumnIndexOrThrow(AleyaContract.COLUMN_TRANSLATION)),
                it.getString(it.getColumnIndexOrThrow(AleyaContract.COLUMN_FOOTNOTE))
            )
            ayas.add(aleya)
        }
    }

    Log.d("Database", "Number of ayas retrieved: ${ayas.size}")
    cursor?.close()
    return ayas
}
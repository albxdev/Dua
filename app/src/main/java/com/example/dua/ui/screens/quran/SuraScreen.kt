package com.example.dua.ui.screens.quran

import android.database.sqlite.SQLiteDatabase
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dua.database.getAyasBySuraNumber
import com.example.dua.database.getQuranSuraByNumber
import com.example.dua.models.Aleya
import com.example.dua.models.QuranSura
import kotlinx.coroutines.launch

@Composable
fun SuraScreen(suraNumber: Int, database: SQLiteDatabase) {
    val coroutineScope = rememberCoroutineScope()

    var expanded by remember { mutableStateOf(false) }
    var sura by remember { mutableStateOf<QuranSura?>(null) }
    var ayas by remember { mutableStateOf<List<Aleya>>(emptyList()) }

    LaunchedEffect(suraNumber) {
        coroutineScope.launch {
            sura = getQuranSuraByNumber(database, suraNumber)
            ayas = getAyasBySuraNumber(database, suraNumber)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White), // Fondo blanco
    ) {
        sura?.let { currentSura ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = currentSura.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(1.dp))
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Expand/Collapse")
                }
            }

            Spacer(modifier = Modifier.height(1.dp)) // Increased spacer size

            Column( // Wrap revelation and juz info in a separate column
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${currentSura.revelation}", // Assuming revelation is renamed to revelationPlace
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "Juz: ${currentSura.juz}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp) // Add some spacing between revelation and juz
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Add spacing before aya cards

            SuraDetailsCard(currentSura, ayas)
        }
    }
}



@Composable
fun SuraDetailsCard(sura: QuranSura, ayas: List<Aleya>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Fondo blanco
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(ayas.size) { index ->
                    AyaCard(ayas[index])
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun AyaCard(aya: Aleya) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF008000)) // Fondo verde
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Aya ${aya.number}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Texto blanco
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = aya.arabicText,
                fontSize = 30.sp,
                color = Color.White, // Texto blanco
                lineHeight = 55.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = aya.transliteration,
                fontSize = 16.sp,
                color = Color.White // Texto blanco
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = aya.translation,
                fontSize = 16.sp,
                color = Color.White // Texto blanco
            )
        }
    }
}

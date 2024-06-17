package com.example.dua.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dua.R


// Define la función que representa la pantalla princip al del Corán

/**
 * Función composable para la pantalla del Corán.
 * @param navController Controlador de navegación para la navegación entre pantallas.
 * @param openLogin Función para abrir la pantalla de inicio de sesión.
 * @param openHome Función para abrir la pantalla de inicio.
 */

@Composable
fun QuranScreen(navController: NavController, openHome: () -> Unit, openLogin: () -> Unit, openSura: (Int) -> Unit, openPrayers: () -> Unit) {


    val activeOption =
        remember { mutableStateOf("Sura") }     // Estado mutable para almacenar la opción activa en la barra de navegación


// Lista de Suras del Corán con su información
    /*
     * Representación composable de una sura del Corán.
     * @param number Número de la sura.
     * @param name Nombre de la sura.
     * @param translation Traducción o significado de la sura.
     * @param iconId ID del recurso de imagen para el icono de la sura.
     */

    val suras = listOf(
        Sura(1, "Al-Fātiĥah", "La Apertura", R.drawable.al_fatiha),
        Sura(2, "Al-Baqarah", "La Vaca", R.drawable.al_baqara),
        Sura(3, "'Āli `Imrān", "La Familia de Imran", R.drawable.al_imran),
        Sura(4, "An-Nisā'", "Las Mujeres", R.drawable.an_nisa),
        Sura(5, " Al-Mā'idah", "La mesa servida", R.drawable.al_maidah),
        Sura(6, "AAl-'An`ām", "Los rebaños", R.drawable.al_anam),
        Sura(7, "Al-'A`rāf", "Los lugares elevados", R.drawable.al_araf),
        Sura(8, " Al-'Anfāl", "El botín", R.drawable.al_anfal),
        Sura(9, "At-Tawbah", "El arrepentimiento", R.drawable.at_tawbah),
        Sura(10, "Yūnis", "Jonás", R.drawable.yunus),
        Sura(11, "Hūd", "Hud", R.drawable.hud),
        Sura(12, "Yūsuf", "José", R.drawable.yusuf),
        Sura(13, "Ar-Ra`d", "El trueno", R.drawable.ar_rad),
        Sura(14, " 'Ibrāhīm", "Ibrahim", R.drawable.ibrahim),
        Sura(15, "Al-Ĥijr", "Al-Hichr [el nombre de la montaña]", R.drawable.al_hijr),
        Sura(16, "An-Naĥl", "Las abejas", R.drawable.an_nahl),
        Sura(17, "Al-'Isrā'", "El viaje nocturno", R.drawable.al_isra),
        Sura(18, "Al-Kahf", "La caverna", R.drawable.al_kahf),
        Sura(19, "Maryam", "Maríam", R.drawable.maryam),
        Sura(20, "Ţāhā", "Ta Ha", R.drawable.taha),


        // Se agregarán el resto de las Suras del Quran aquí
    )

    // Define una columna que contiene la pantalla principal de la aplicación
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(15.dp))

    ) {

        // Fila superior que contiene el título y el icono de búsqueda
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                 horizontalArrangement = Arrangement.SpaceAround,
                 verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f) // La columna ocupa el espacio disponible
            ) {
                Text(
                    text = "Salaam",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = "Fecha Islámica", // Texto que se muestra
                    fontSize = 18.sp, // Tamaño de la fuente
                    textAlign = TextAlign.Start // Alinea el texto al inicio
                )
            }

            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Busqueda",
                modifier = Modifier
                    .size(36.dp)
                    .clickable { /* Handle click on lupa */ }

            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fila de opciones de navegación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(15.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val options = listOf("Sura", "Juz", "Playlist", "My Quran")
            options.forEach { option ->
                val backgroundColor = animateColorAsState(
                    targetValue = if (activeOption.value == option) Color(0xFFFBBC05) else Color.Transparent,
                    label = "backgroundColorAnimation"
                )
                Text(
                    text = option,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f, fill = false)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(backgroundColor.value)
                        .clickable { activeOption.value = option }
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    maxLines = 1,
                    softWrap = false,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen del banner
        Image(
            painter = painterResource(id = R.drawable.bannerwebp),
            contentDescription = "Banner",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(31.dp))
                .border(2.dp, Color(0xFF309C2D), RoundedCornerShape(31.dp))
                .height(200.dp),
                contentScale = ContentScale.Crop

        )

        Spacer(modifier = Modifier.height(16.dp))


        // Columna de contenido desplazable que muestra las tarjetas de las Suras del Corán
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(suras) { sura ->
                QuranSuraCard(sura = sura) {
                    navController.navigate("sura/${sura.number}")
                }
            }
        }


        Spacer(modifier = Modifier.height(32.dp))

        // Row que contiene los íconos de navegación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(21.dp))
                .border(2.dp, Color(0xFFFFCE00), RoundedCornerShape(35.dp))
                .background(color = Color.White)
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(48.dp)
                    .clickable { openHome() }
            )
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(48.dp)
                    .clickable { openLogin() }
            )
            Icon(
                painter = painterResource(id = R.drawable.quran),
                contentDescription = "Quran",
                modifier = Modifier
                    .size(48.dp)
                    .clickable { /* Acción al hacer clic en el ícono del Corán */ }
            )
            Icon(
                painter = painterResource(id = R.drawable.praying),
                contentDescription = "Praying",
                modifier = Modifier.size(48.dp)
                .clickable { openPrayers()}

            )
        }
    }
}

@Composable
fun QuranSuraCard(sura: Sura, onSuraClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.clickable(onClick = onSuraClicked)
                .background(Color(0xFF008000))
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${sura.number}.",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White),
                modifier = Modifier.padding(end = 16.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = sura.transliteration,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFBBC05))
                )
                Text(
                    text = sura.nameSpanish,
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = sura.imageRes),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

// Clase de datos que representa una sura del Corán con su información asociada
data class Sura(
    val number: Int,
    val transliteration: String,
    val nameSpanish: String,
    val imageRes: Int
)
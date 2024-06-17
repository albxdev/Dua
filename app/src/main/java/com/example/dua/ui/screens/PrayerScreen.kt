package com.example.dua.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dua.R

@Composable
fun PrayerScreen(
    navController: NavController,
    openHome: () -> Unit,
    openLogin: () -> Unit,
    openQuran: () -> Unit,
    openSura: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize() // La columna llena todo el tamaño disponible
            .padding(16.dp) // Agrega un padding de 16 dp alrededor de la columna //
    ) {
// Ciudad, iconos de compartir y mostrar todas las oraciones
        Row(
            modifier = Modifier.fillMaxWidth(), // La fila llena todo el ancho disponible
            verticalAlignment = Alignment.CenterVertically // Alinea los elementos verticalmente al centro
        ) {
            Column(
                modifier = Modifier.weight(1f) // La columna ocupa el espacio disponible
            ) {
                Text(
                    text = "Barranquilla", // Texto que se muestra
                    fontSize = 24.sp, // Tamaño de la fuente
                    textAlign = TextAlign.Start, // Alinea el texto al inicio
                    modifier = Modifier.padding(bottom = 8.dp) // Agrega un padding inferior de 8 dp
                )
// Texto de la fecha islámica
                Text(
                    text = "Muslim World League (18.0°/17.0°)",
                    fontSize = 15.sp, // Tamaño de la fuente
                    textAlign = TextAlign.Start // Alinea el texto al inicio
                )
            }
            Row {
                IconButton(onClick = { /* Acción para compartir / }) {
Icon(Icons.Default.Share, contentDescription = "Compartir")
}
IconButton(onClick = { / Acción para mostrar todas las oraciones */ }) {
                    Icon(
                        Icons.AutoMirrored.Filled.List,
                        contentDescription = "Mostrar todas las oraciones"
                    )
                }
            }
        }

                     // Banner animado
        Image(
            painter = painterResource(id = R.drawable.banner_prayers),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).height(100.dp),
            contentScale = ContentScale.Crop
        )

        // Fecha gregoriana e islámica
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 9.dp)
        ) {
            Column(
                modifier = Modifier.padding(9.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "10 de Junio de 2024", style = MaterialTheme.typography.headlineSmall)
                Text(text = "22 Dhu al-Qi'dah 1445", style = MaterialTheme.typography.titleSmall)
            }
        }

        // Horarios de oración
        val prayers = listOf(
            "Fajr" to "5:30 AM",
            "Sunrise" to "6:45 AM",
            "Duhr" to "1:15 PM",
            "Asr" to "4:45 PM",
            "Maghrib" to "8:00 PM",
            "Isha'a" to "9:15 PM"
        )

        Column{
            prayers.forEach { (prayer, time) ->
                PrayerTimeCard(prayer = prayer, time = time, isSoundOn = true)
            }
        }

        Spacer(modifier = Modifier.weight(1f)) // El espaciador ocupa el espacio disponible


        // Íconos de navegación
        Row(
            modifier = Modifier
                .fillMaxWidth() // La fila llena todo el ancho disponible
                .clip(RoundedCornerShape(16.dp)) // Recorta la fila con esquinas redondeadas de 16 dp
                .border(width = 2.dp, color = Color(0xFFFFCE00), shape = RoundedCornerShape(35.dp)) // Agrega un borde de 2 dp de ancho y color amarillo con esquinas redondeadas de 35 dp
                .background(color = Color.White) // Fondo blanco para la fila
                .padding(5.dp), // Padding interno de 5 dp
            horizontalArrangement = Arrangement.SpaceAround, // Distribuye los elementos horizontalmente de manera uniforme
            verticalAlignment = Alignment.CenterVertically // Alinea los elementos verticalmente al centro
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home",
                tint = Color.Unspecified,
                modifier = Modifier.size(48.dp).clickable { openHome() }
            )
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier.size(48.dp).clickable { openLogin() }
            )
            Icon(
                painter = painterResource(id = R.drawable.quran),
                contentDescription = "Quran",
                modifier = Modifier.size(48.dp).clickable { openQuran() }
            )
            Icon(
                painter = painterResource(id = R.drawable.praying),
                contentDescription = "Praying",
                modifier = Modifier.size(48.dp).clickable { openSura(0) }
            )

        }
    }
}
@Composable
fun PrayerTimeCard(prayer: String, time: String, isSoundOn: Boolean) {
    val textColor = Color(0xFFEFA81B)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black) // Apply black color to the card background

            ) {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = prayer,
                            style = MaterialTheme.typography.headlineSmall,
                            color = textColor
                        )
                        Text(
                            text = time,
                            style = MaterialTheme.typography.titleSmall,
                            color = textColor

                        )
                    }
                    Icon(
                        imageVector = if (isSoundOn) Icons.Default.Notifications else Icons.Default.NotificationsOff,
                        contentDescription = if (isSoundOn) "Sonido activado" else "Sonido desactivado",
                        tint = textColor,

                        )
                }
            }
}

// Asegúrate de definir las funciones openHome y openLogin


fun openHome() {
    // Implementa la navegación a la pantalla de inicio
}

fun openLogin() {
    // Implementa la navegación a la pantalla de login
}
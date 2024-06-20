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
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.dua.ui.activities.CityCoordinatesResponse
import com.example.dua.ui.activities.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PrayerScreen(
    navController: NavController,
    openHome: () -> Unit,
    openLogin: () -> Unit,
    openQuran: () -> Unit,
    openSura: (Int) -> Unit
) {
    var prayerTimings by remember { mutableStateOf<CityCoordinatesResponse?>(null) }
    var selectedCity by remember { mutableStateOf("Barranquilla") }
    var isDialogOpen by remember { mutableStateOf(false) }


            LaunchedEffect(selectedCity) {
                fetchPrayerTimings(selectedCity) { response ->
                    if (response.isSuccessful) {
                        prayerTimings = response.body()
                    } else {
                        println("API Error: ${response.message()}")  // Depuración
                    }
                }
            }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Ciudad, iconos de compartir y mostrar todas las oraciones
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "$selectedCity",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable {
                        isDialogOpen = true
                    }
                )
                Text(
                    text = "Muslim World League (18.0°/17.0°)",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start
                )
            }
            Row {
                IconButton(onClick = { /* Acción para compartir */ }) {
                    Icon(Icons.Default.Share, contentDescription = "Compartir")
                }
                IconButton(onClick = { /* Acción para mostrar todas las oraciones */ }) {
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
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .height(100.dp),
            contentScale = ContentScale.Crop
        )

        // Fecha gregoriana e islámica
        prayerTimings?.let { timings ->
            val hijriDate = timings.data[0].date.hijri
            val gregorianDate = timings.data[0].date.gregorian.date
            val hijriMonthName = hijriDate.month?.text ?: hijriDate.month?.number.toString()

            println("Hijri Date: $hijriDate")  // Depuración
            println("Gregorian Date: $gregorianDate")  // Depuración



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally // Alineación central horizontal para la columna
                ) {
                    // Fecha hijri (islámica)
                    Text(
                        text = "${hijriDate.day} $hijriMonthName ${hijriDate.year}",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEFA81B),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )

                    // Fecha gregoriana
                    Text(
                        text = gregorianDate,
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color(0xFFEFA81B),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }


            // Horarios de oración
            val prayers = listOf(
                "Fajr" to timings.data[0].timings.Fajr,
                "Sunrise" to timings.data[0].timings.Sunrise,
                "Dhuhr" to timings.data[0].timings.Dhuhr,
                "Asr" to timings.data[0].timings.Asr,
                "Maghrib" to timings.data[0].timings.Maghrib,
                "Isha" to timings.data[0].timings.Isha
            )

            Column {
                prayers.forEach { (prayer, time) ->
                    PrayerTimeCard(
                        prayer = prayer,
                        time = time,
                        isSoundOn = true,
                        prayerTimings = prayerTimings
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Íconos de navegación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(width = 2.dp, color = Color(0xFFFFCE00), shape = RoundedCornerShape(35.dp))
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
                    .clickable { openQuran() }
            )
            Icon(
                painter = painterResource(id = R.drawable.praying),
                contentDescription = "Praying",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {  }
            )
        }

        // Diálogo de búsqueda de ciudad
        if (isDialogOpen) {
            CitySearchDialog(
                onCitySelected = { newCity ->
                    selectedCity = newCity
                    isDialogOpen = false
                    fetchPrayerTimings(selectedCity) { response ->
                        if (response.isSuccessful) {
                            prayerTimings = response.body()
                        } else {
                            // Manejar caso de respuesta no exitosa si es necesario
                        }
                    }
                },
                onDismiss = { isDialogOpen = false }
            )
        }
    }
}

@Composable
fun CitySearchDialog(
    onCitySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var cityName by remember { mutableStateOf("") }


// Define custom button colors
    val customButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFEFA81B), // Color de fondo del botón
        contentColor = Color.White // Color del texto del botón
    )

// Aquí definimos el color de fondo del AlertDialog
    Surface(color = Color.Black) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text("Ingresa tu ciudad", color = Color(0xFFEFA81B)) // Color del texto del título
            },
            text = {
                Column {
                    Text("Ciudad:", color = Color(0xFFEFA81B)) // Color del texto
                    TextField(
                        value = cityName,
                        onValueChange = { cityName = it },
                        modifier = Modifier.background(color = Color.Gray) // Color de fondo del campo de texto
                    )
                }
            },
            confirmButton = {
                // Aplica colores personalizados al botón de aceptar
                Button(
                    onClick = {
                        onCitySelected(cityName)
                        onDismiss()
                    },
                    colors = customButtonColors
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                // Aplica colores personalizados al botón de cancelar
                Button(
                    onClick = { onDismiss() },
                    colors = customButtonColors
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

private fun fetchPrayerTimings(city: String, onResponse: (Response<CityCoordinatesResponse>) -> Unit) {
    val apiService = RetrofitClient.prayerTimesApiService


// Obtener el mes y año actuales
    val currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1
    val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)

// Realizar la llamada a la API
    apiService.getPrayerTimesByCity(currentYear, currentMonth, city, "Colombia")
        .enqueue(object : Callback<CityCoordinatesResponse> {
            override fun onResponse(call: Call<CityCoordinatesResponse>, response: Response<CityCoordinatesResponse>) {
                onResponse(response)
            }

            override fun onFailure(call: Call<CityCoordinatesResponse>, t: Throwable) {
                // Manejar errores aquí
                println("Error en la llamada a la API: ${t.message}")
                // Aquí puedes establecer isLoading en false, isError en true, o cualquier otra lógica de manejo de errores necesaria
            }
        })
}

@Composable
fun PrayerTimeCard(prayer: String, time: String, isSoundOn: Boolean, prayerTimings: CityCoordinatesResponse?) {
    val textColor = Color(0xFFEFA81B)


    // Función para limpiar el formato de la hora (eliminar el desfase de zona horaria)
    fun cleanUpTime(rawTime: String): String {
        return rawTime.replace(" (-05)", "")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Row(  // Removed fillMaxWidth
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.8f) // Adjusted weight using fraction
                    .fillMaxWidth() // Allow Column to occupy remaining space
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 21.dp), // Add padding to the left
                    text = prayer,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                    color = textColor,
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    modifier = Modifier
                        .padding(start = 21.dp), // Add padding to the left
                    text = cleanUpTime(time),
                    style = MaterialTheme.typography.titleSmall.copy(fontSize = 18.sp),
                    color = textColor,

                    )
            }
            IconButton(
                onClick = { /* Implementa la acción de activar/desactivar sonido */ },
                modifier = Modifier
                    .padding(end = 21.dp) // Add padding to the left
                    .size(24.dp) // Adjusted icon size using dp

            ) {
                Icon(
                    imageVector = if (isSoundOn) Icons.Default.Notifications else Icons.Default.NotificationsOff,
                    contentDescription = if (isSoundOn) "Sonido activado" else "Sonido desactivado",
                    tint = textColor
                )
            }
        }
    }
}

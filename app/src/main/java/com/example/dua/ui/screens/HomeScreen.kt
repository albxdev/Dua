package com.example.dua.ui.screens // Se define el paquete donde se encuentra el archivo

// Importaciones necesarias para Jetpack Compose
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.dua.R


// Se define una función composable llamada HomeScreen
    @Composable
    fun HomeScreen(navController: NavController, openLogin: () -> Unit, openQuran: () -> Unit, openPrayers: () -> Unit, openSura: (Int) -> Unit, openSettings: () -> Unit) {
        // Uso del navController como un placeholder para evitar la advertencia
        navController.currentBackStackEntry?.arguments?.getString("key")

        // Configuración de la columna principal que ocupa toda la pantalla y tiene padding
        Column(
            modifier = Modifier
                .fillMaxSize() // La columna llena todo el tamaño disponible
                .padding(16.dp) // Agrega un padding de 16 dp alrededor de la columna
                .clip(RoundedCornerShape(15.dp))

        ) {
            // Fila para el saludo y la configuración
            Row(
                modifier = Modifier.fillMaxWidth(), // La fila llena todo el ancho disponible
                verticalAlignment = Alignment.CenterVertically // Alinea los elementos verticalmente al centro
            ) {
                // Columna para el texto de saludo y fecha islámica
                Column(
                    modifier = Modifier.weight(1f) // La columna ocupa el espacio disponible
                ) {
                    // Texto de saludo
                    Text(
                        text = "Salaam", // Texto que se muestra
                        fontSize = 24.sp, // Tamaño de la fuente
                        textAlign = TextAlign.Start, // Alinea el texto al inicio
                        modifier = Modifier.padding(bottom = 8.dp) // Agrega un padding inferior de 8 dp
                    )
                    // Texto de la fecha islámica
                    Text(
                        text = "Fecha Islámica", // Texto que se muestra
                        fontSize = 18.sp, // Tamaño de la fuente
                        textAlign = TextAlign.Start // Alinea el texto al inicio
                    )
                }
                // Imagen de configuración con forma circular y clicable
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.settings) // Reemplace "R.drawable.settings" con el nombre de su recurso GIF
                        .decoderFactory(ImageDecoderDecoder.Factory()) // Decodifica imágenes GIF
                        .build(),
                    contentDescription = "Settings", // Descripción de contenido para accesibilidad
                    modifier = Modifier
                        .size(36.dp) // Tamaño de la imagen
                        .clip(CircleShape) // Recorta la imagen en forma circular
                        .clickable { openSettings() } // Hace la imagen clicable y llama a openSettings al hacer clic, abre la pantalla Settings
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Imagen del banner con esquinas redondeadas
            Image(
                painter = painterResource(id = R.drawable.banner), // Carga la imagen desde los recursos
                contentDescription = "Banner", // Descripción de contenido para accesibilidad
                modifier = Modifier
                    .fillMaxWidth() // La imagen llena todo el ancho disponible
                    .clip(RoundedCornerShape(31.dp)).
                    height(300.dp),
                    contentScale = ContentScale.Crop
            )

            // Espaciador flexible para empujar el contenido hacia abajo
            Spacer(modifier = Modifier.weight(1f)) // El espaciador ocupa el espacio disponible

            // Fila para los íconos de navegación
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
                // Icono de Home
                Icon(
                    painter = painterResource(id = R.drawable.home), // Carga el icono desde los recursos
                    contentDescription = "Home", // Descripción de contenido para accesibilidad
                    tint = Color.Unspecified, // Sin color de tinte
                    modifier = Modifier.size(48.dp) // Tamaño del icono
                )
                // Icono de Profile que navega a la pantalla de login al ser clicado
                Icon(
                    painter = painterResource(id = R.drawable.profile), // Carga el icono desde los recursos
                    contentDescription = "Profile", // Descripción de contenido para accesibilidad
                    modifier = Modifier
                        .size(48.dp) // Tamaño del icono
                        .clickable {
                            openLogin() // Navega a la pantalla de login al tocar el icono
                        }
                )
                // Icono de Quran que navega a la pantalla del Quran al ser clicado
                Icon(
                    painter = painterResource(id = R.drawable.quran), // Carga el icono desde los recursos
                    contentDescription = "Quran", // Descripción de contenido para accesibilidad
                    modifier = Modifier
                        .size(48.dp) // Tamaño del icono
                        .clickable {
                            openQuran() // Navega a la pantalla del Quran al tocar el icono
                    }
            )
            // Icono de Praying
            Icon(
                painter = painterResource(id = R.drawable.praying), // Carga el icono desde los recursos
                contentDescription = "Praying", // Descripción de contenido para accesibilidad
                modifier = Modifier
                    .size(48.dp) // Tamaño del icono
                    .clickable {openPrayers()}

            )
        }
    }
}
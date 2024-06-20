// Package declaracion
package com.example.dua.ui.activities

// Imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dua.database.QuranDatabaseHelper
import com.example.dua.managers.FirebaseAuthManager
import com.example.dua.ui.screens.HomeScreen
import com.example.dua.ui.screens.LoginScreen
import com.example.dua.ui.screens.PrayerScreen
import com.example.dua.ui.screens.QuranScreen
import com.example.dua.ui.screens.RegisterProcess
import com.example.dua.ui.screens.quran.SuraScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


// Clase principal HomeActivity que hereda de ComponentActivity
class HomeActivity : ComponentActivity() {


    private lateinit var auth: FirebaseAuth // Variable para la autenticación de Firebase
    private lateinit var dbHelper: QuranDatabaseHelper



    override fun onCreate(savedInstanceState: Bundle?) { // Método onCreate
        super.onCreate(savedInstanceState) // Llamada al método onCreate de la superclase
        auth = Firebase.auth // Inicialización de la autenticación con Firebase Auth
        auth = Firebase.auth // Inicialización de la autenticación con Firebase Auth
        dbHelper = QuranDatabaseHelper(this)




        // Define la interfaz de usuario utilizando Jetpack Compose
        setContent {

            // Ejemplo de uso de las clases y objetos


            MaterialTheme { // Establece el tema Material
                val navController =
                    rememberNavController() // Conserva el estado del controlador de navegación

                // Configura el sistema de navegación con NavHost
                NavHost(
                    navController,
                    startDestination = "home"
                ) { // Define el contenedor de destinos de navegación
                    // Define los composables para cada destino en la navegación
                    composable("home") { // Pantalla principal de inicio
                        HomeScreen(
                            // Llama a la pantalla de inicio
                            navController = navController, // Pasa el controlador de navegación
                            openLogin = { navController.navigate("login") }, // Abre la pantalla de inicio de sesión
                            openQuran = { navController.navigate("quran") }, // Abre la pantalla del Corán
                            openSura = { suraNumber: Int -> navController.navigate("sura/$suraNumber") },
                            openPrayers = { navController.navigate("prayers") }, // Abre la pantalla de Oraciones
                            openSettings = { navController.navigate("settings") }, // Abre la pantalla de configuraciones


                        )
                    }
                    // Agrega aquí el composable para la pantalla de configuraciones
                    composable("profile") {
                        // Aquí iría la pantalla de perfil
                    }

                    composable("login") { // Pantalla de inicio de sesión
                        LoginScreen( // Llama a la pantalla de inicio de sesión
                            onLoginClicked = { userEmail, userPassword -> // Maneja el clic en el botón de inicio de sesión
                                signInUser(userEmail, userPassword, navController) // Inicia sesión
                            },
                            navigateToRegister = { navController.navigate("register") } // Navega a la pantalla de registro
                        )
                    }

                    composable("quran") { // Pantalla del Corán
                        QuranScreen(
                            // Llama a la pantalla del Corán
                            navController = navController, // Pasa el controlador de navegación
                            openHome = { navController.navigate("home") }, // Abre la pantalla principal
                            openLogin = { navController.navigate("login") }, // Abre la pantalla de inicio de sesión
                            openSura = { suraNumber -> navController.navigate("sura/${suraNumber}") },
                            openPrayers = { navController.navigate("prayers") }, // Abre la pantalla principal
                        )
                    }

                    composable("sura/{suraNumber}") { backStackEntry ->
                        val suraNumber =
                            backStackEntry.arguments?.getString("suraNumber")?.toIntOrNull()
                        if (suraNumber != null) {
                            SuraScreen(
                                suraNumber = suraNumber,
                                database = dbHelper.writableDatabase
                            )
                        } else {
                            // Handle case where sura number cannot be obtained from arguments
                        }
                    }




                    composable("prayers") { // Pantalla de oración
                        // Aquí iría la pantalla de oración
                        PrayerScreen(
                            navController = navController, // Pasa el controlador de navegación
                            openHome = { navController.navigate("home") }, // Abre la pantalla principal
                            openLogin = { navController.navigate("login") }, // Abre la pantalla de inicio de sesión
                            openQuran = { navController.navigate("quran") }, // Abre la pantalla del Corán
                            openSura = { suraNumber: Int -> navController.navigate("sura/$suraNumber") },
                        )
                    }
                    composable("settings") { SettingsScreen() }







                    composable("register") { // Pantalla de registro
                        RegisterProcess (navController = navController) { nombre, email, password, pais, zonaHoraria, notificaciones -> // Proceso de registro
                            val (hash, salt) = hashPassword(password) // Genera el hash seguro de la contraseña
                            val fechaDeRegistro = SimpleDateFormat(
                                "yyyy-MM-dd",
                                Locale.getDefault()
                            ).format(Date()) // Obtiene la fecha actual
                            lifecycleScope.launch { // Lanza una corrutina en el ámbito del ciclo de vida
                                val isSuccess =
                                    FirebaseAuthManager.registerUser( // Registra al usuario en Firebase Auth
                                        nombre,
                                        email,
                                        hash.toHexString(),
                                        salt.toHexString(), // Datos de registro
                                        pais,
                                        zonaHoraria,
                                        notificaciones,
                                        fechaDeRegistro // Más datos de registro
                                    )
                                if (isSuccess) { // Si el registro es exitoso
                                    navController.navigate("login") // Navega a la pantalla de inicio de sesión
                                } else { // Si el registro falla
                                    // Mostrar mensaje de error
                                }
                            }
                        }
                    }


                }
            }
        }
    }


    private fun signInUser(email: String, password: String, navController: NavController) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    navController.navigate("home")
                } else {
                    // Mostrar mensaje de error
                }
            }
    }


    // Función para generar un hash seguro de la contraseña
    private fun hashPassword(password: String): Pair<ByteArray, ByteArray> {
        try {
            val salt = generateSalt() // Genera un salt aleatorio
            val spec = PBEKeySpec(
                password.toCharArray(),
                salt,
                10000,
                256
            ) // Especifica la clave derivada de la contraseña
            val factory =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256") // Obtiene una instancia de la fábrica de claves secretas
            val hash = factory.generateSecret(spec).encoded // Genera el hash de la contraseña
            return hash to salt // Devuelve el hash y el salt
        } catch (e: Exception) { // Captura excepciones
            throw RuntimeException(
                "Error generating password hash",
                e
            ) // Lanza una excepción en caso de error
        }
    }

    // Extensión de ByteArray para convertirlo en una cadena hexadecimal
    private fun ByteArray.toHexString(): String {
        return joinToString("") { "%02x".format(it) } // Convierte el ByteArray a una cadena hexadecimal
    }

    // Función para generar un salt aleatorio
    private fun generateSalt(): ByteArray {
        val random = SecureRandom() // Genera un objeto SecureRandom
        val salt = ByteArray(16) // Crea un ByteArray de 16 bytes
        random.nextBytes(salt) // Genera bytes aleatorios y los coloca en el ByteArray
        return salt // Devuelve el salt generado
    }


    // Anotación @Preview para previsualizar la interfaz de usuario
    @Preview(showBackground = true)
    @Composable
    // Función que muestra la interfaz de usuario predeterminada
    fun DefaultPreview() {
        MaterialTheme { // Establece el tema Material
            val navController =
                rememberNavController() // Conserva el estado del controlador de navegación

            // Configura el sistema de navegación con NavHost
            NavHost(
                navController,
                startDestination = "home"
            ) { // Define el contenedor de destinos de navegación
                // Define los composables para cada destino en la navegación
                composable("home") { // Pantalla principal de inicio
                    HomeScreen(
                        // Llama a la pantalla de inicio
                        navController = navController, // Pasa el controlador de navegación
                        openLogin = {}, // Abre la pantalla de inicio de sesión
                        openQuran = {}, // Abre la pantalla del Quran
                        openSura = {}, // Abre la pantalla del Corán
                        openPrayers = {}, // Abre la pantalla de oraciones.
                        openSettings = {} // Abre la pantalla de configuraciones
                    )
                }



                composable("login") { // Pantalla de inicio de sesión
                    LoginScreen( // Llama a la pantalla de inicio de sesión
                        onLoginClicked = { userEmail, userPassword -> // Maneja el clic en el botón de inicio de sesión
                            signInUser(userEmail, userPassword, navController) // Inicia sesión
                        },
                        navigateToRegister = { navController.navigate("register") } // Navega a la pantalla de registro
                    )
                }

                composable("register") { // Pantalla de registro
                    RegisterProcess (navController = navController) { nombre, email, password, pais, zonaHoraria, notificaciones -> // Proceso de registro
                        val (hash, salt) = hashPassword(password) // Genera el hash seguro de la contraseña
                        val fechaDeRegistro = SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault()
                        ).format(Date()) // Obtiene la fecha actual
                        lifecycleScope.launch { // Lanza una corrutina en el ámbito del ciclo de vida
                            val isSuccess =
                                FirebaseAuthManager.registerUser( // Registra al usuario en Firebase Auth
                                    nombre,
                                    email,
                                    hash.toHexString(),
                                    salt.toHexString(), // Datos de registro
                                    pais,
                                    zonaHoraria,
                                    notificaciones,
                                    fechaDeRegistro // Más datos de registro
                                )
                            if (isSuccess) { // Si el registro es exitoso
                                navController.navigate("login") // Navega a la pantalla de inicio de sesión
                            } else { // Si el registro falla
                                // Mostrar mensaje de error
                            }
                        }
                    }
                }


                // Agrega aquí el composable para la pantalla de configuraciones

            }
        }
    }
}

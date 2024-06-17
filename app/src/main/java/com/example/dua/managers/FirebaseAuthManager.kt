// Definición del paquete de la aplicación
package com.example.dua.managers

// Importación de la clase FirebaseAuth desde la biblioteca Firebase Authentication
import com.google.firebase.auth.FirebaseAuth

// Importación de la clase FirebaseFirestore desde la biblioteca Firebase Firestore
import com.google.firebase.firestore.FirebaseFirestore

// Importación de la extensión await para hacer que las llamadas de Firebase sean suspendidas
import kotlinx.coroutines.tasks.await

// Objeto que gestiona las operaciones relacionadas con Firebase Authentication
object FirebaseAuthManager {

    // Función suspendida para registrar un usuario en Firebase Authentication y Firestore
    suspend fun registerUser(
        nombre: String, // Nombre del usuario
        email: String, // Correo electrónico del usuario
        password: String, // Contraseña del usuario
        salt: String, // Salt utilizado para generar el hash de la contraseña
        pais: String, // País del usuario
        zonaHoraria: String, // Zona horaria del usuario
        notificaciones: Boolean, // Preferencia de notificaciones del usuario
        fechaDeRegistro: String // Fecha de registro del usuario
    ): Boolean { // Devuelve true si el registro es exitoso, false si hay un error
        return try { // Maneja cualquier excepción
            val auth = FirebaseAuth.getInstance() // Obtiene una instancia de FirebaseAuth
            val firestore = FirebaseFirestore.getInstance() // Obtiene una instancia de FirebaseFirestore

            // Registra un nuevo usuario con el correo electrónico y la contraseña proporcionados, y espera el resultado
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: throw Exception("User ID is null") // Obtiene el ID de usuario generado

            // Crea un mapa con los datos del usuario
            val userData = mapOf(
                "nombre" to nombre,
                "email" to email,
                "password" to password,
                "salt" to salt,
                "pais" to pais,
                "zonaHoraria" to zonaHoraria,
                "notificaciones" to notificaciones,
                "fechaDeRegistro" to fechaDeRegistro
            )

            // Almacena los datos del usuario en Firestore bajo la colección "users"
            firestore.collection("users").document(userId).set(userData).await()

            true // Registro exitoso
        } catch (e: Exception) {
            false // Error en el registro
        }
    }
}

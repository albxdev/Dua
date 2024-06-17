package com.example.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DatabaseConnection {

    private const val INSTANCE_CONNECTION_NAME = "DuaDB:reflected-jet-425215-v0:southamerica-west1:duadb" // Reemplaza con el nombre de conexión de tu instancia de Cloud SQL
    private const val DATABASE_NAME = "dua" // Reemplaza con el nombre de tu base de datos en Cloud SQL
    private const val USER = "postgres" // Reemplaza con el nombre de usuario de tu instancia de Cloud SQL
    private const val PASSWORD = "Albertx1611!!" // Reemplaza con la contraseña de tu instancia de Cloud SQL

    private const val URL = "jdbc:postgresql://google/$DATABASE_NAME?cloudSqlInstance=$INSTANCE_CONNECTION_NAME&socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=$USER&password=$PASSWORD"

    init {
        try {
            Class.forName("org.postgresql.Driver")
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Failed to load PostgreSQL driver", e)
        }
    }

    private suspend fun getConnection(): Connection = withContext(Dispatchers.IO) {
        return@withContext DriverManager.getConnection(URL)
    }

    suspend fun registerUser(
        nombre: String,
        email: String,
        hash: String,
        salt: String,
        pais: String,
        zonaHoraria: String,
        notificaciones: Boolean
    ): Boolean = withContext(Dispatchers.IO) {
        val connection = getConnection()
        val statement: PreparedStatement = connection.prepareStatement(
            "INSERT INTO usuarios (nombre, email, password_hash, salt, fecha_registro, pais, zona_horaria, notificaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        )

        statement.setString(1, nombre)
        statement.setString(2, email)
        statement.setString(3, hash)
        statement.setString(4, salt)
        statement.setString(5, getCurrentDateTime())
        statement.setString(6, pais)
        statement.setString(7, zonaHoraria)
        statement.setBoolean(8, notificaciones)

        val rowsInserted = statement.executeUpdate()
        statement.close()
        connection.close()
        return@withContext rowsInserted > 0
    }

    private fun getCurrentDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return current.format(formatter)
    }
}

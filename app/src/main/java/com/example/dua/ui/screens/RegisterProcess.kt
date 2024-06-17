package com.example.dua.ui.screens // Define el nombre del paquete donde se encuentra este archivo

import androidx.compose.foundation.layout.Arrangement // Importa el diseño de los elementos de la interfaz de usuario
import androidx.compose.foundation.layout.Column // Importa el contenedor de columnas para organizar elementos verticalmente
import androidx.compose.foundation.layout.Row // Importa el contenedor de filas para organizar elementos horizontalmente
import androidx.compose.foundation.layout.Spacer // Importa un espacio en blanco ajustable
import androidx.compose.foundation.layout.fillMaxSize // Importa una función para llenar completamente el tamaño disponible
import androidx.compose.foundation.layout.fillMaxWidth // Importa una función para llenar completamente el ancho disponible
import androidx.compose.foundation.layout.padding // Importa una función para agregar relleno alrededor de los elementos
import androidx.compose.foundation.layout.size // Importa una función para definir el tamaño de un elemento
import androidx.compose.foundation.text.KeyboardOptions // Importa opciones de teclado para campos de texto
import androidx.compose.material3.Button // Importa un botón con estilo de Material Design 3
import androidx.compose.material3.Checkbox // Importa un checkbox con estilo de Material Design 3
import androidx.compose.material3.OutlinedTextField // Importa un campo de texto delineado con estilo de Material Design 3
import androidx.compose.material3.Text // Importa un componente de texto con estilo de Material Design 3
import androidx.compose.runtime.Composable // Importa la anotación Composable para definir funciones de composición
import androidx.compose.runtime.mutableStateOf // Importa una función para crear un estado mutable en Compose
import androidx.compose.runtime.remember // Importa una función para recordar valores entre recomposiciones
import androidx.compose.ui.Modifier // Importa la clase Modifier para aplicar modificadores a los elementos de la interfaz de usuario
import androidx.compose.ui.text.input.KeyboardType // Importa los tipos de teclado para campos de texto
import androidx.compose.ui.text.input.PasswordVisualTransformation // Importa la transformación visual para ocultar contraseñas
import androidx.compose.ui.unit.dp // Importa una clase que representa una dimensión en píxeles o píxeles independientes de la densidad
import androidx.navigation.NavController

@Composable // Anotación que indica que esta función es un composable, lo que significa que puede ser utilizada para construir la interfaz de usuario de la aplicación con Jetpack Compose.
fun RegisterProcess(navController: NavController, onRegisterClicked: (String, String, String, String, String, Boolean) -> Unit) { // Definición de la función 'RegisterProcess' que acepta un lambda como parámetro.
    // El lambda recibe seis argumentos: nombre, email, contraseña, país, zona horaria y estado de notificaciones, todos como cadenas de texto excepto el estado de notificaciones que es un booleano.
    // Declara estados mutables para los datos del formulario de registro

    val nombre =
        remember { mutableStateOf("") } // Define un estado mutable 'nombre' que almacenará el valor del nombre ingresado por el usuario
    val email =
        remember { mutableStateOf("") } // Define un estado mutable 'email' que almacenará el valor del correo electrónico ingresado por el usuario
    val password =
        remember { mutableStateOf("") } // Define un estado mutable 'password' que almacenará el valor de la contraseña ingresada por el usuario
    val pais =
        remember { mutableStateOf("") } // Define un estado mutable 'pais' que almacenará el valor del país ingresado por el usuario
    val zonaHoraria =
        remember { mutableStateOf("") } // Define un estado mutable 'zonaHoraria' que almacenará el valor de la zona horaria ingresada por el usuario
    val notificaciones =
        remember { mutableStateOf(false) } // Define un estado mutable 'notificaciones' que almacenará el estado de las notificaciones seleccionado por el usuario

    // Define la estructura de la interfaz de usuario del formulario de registro

    Column(
        modifier = Modifier
            .fillMaxSize() // Utiliza todo el espacio disponible
            .padding(16.dp), // Agrega un relleno de 16 píxeles alrededor de los elementos
        verticalArrangement = Arrangement.Center // Organiza verticalmente los elementos en el centro
    ) {

        // Campo de texto delineado para el nombre

        OutlinedTextField(
            value = nombre.value, // Establece el valor del campo de texto del nombre al valor actual almacenado en 'nombre.value'
            onValueChange = { nombre.value = it }, // Actualiza el valor del nombre cuando cambia
            label = { Text("Nombre") }, // Etiqueta del campo de texto
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible
        )

        // Campo de texto delineado para el correo electrónico

        OutlinedTextField(
            value = email.value, // Establece el valor del campo de texto de correo electrónico al valor actual almacenado en 'email.value'
            onValueChange = {
                email.value = it
            }, // Actualiza el valor del correo electrónico cuando cambia
            label = { Text("Email") }, // Etiqueta del campo de texto
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible
        )

        // Campo de texto delineado para la contraseña

        OutlinedTextField(
            value = password.value, // Establece el valor del campo de texto de contraseña al valor actual almacenado en 'password.value'
            onValueChange = {
                password.value = it
            }, // Actualiza el valor de la contraseña cuando cambia
            label = { Text("Contraseña") }, // Etiqueta del campo de texto
            visualTransformation = PasswordVisualTransformation(), // Oculta la contraseña
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Establece el teclado en modo contraseña
            singleLine = true, // Permite solo una línea de texto
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible
        )

        // Campo de texto delineado para el país

        OutlinedTextField(
            value = pais.value,
            onValueChange = { pais.value = it }, // Actualiza el valor del país cuando cambia
            label = { Text("País") }, // Etiqueta del campo de texto
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible
        )

        // Campo de texto delineado para la zona horaria

        OutlinedTextField(
            value = zonaHoraria.value,
            onValueChange = {
                zonaHoraria.value = it
            }, // Actualiza el valor de la zona horaria cuando cambia
            label = { Text("Zona Horaria") }, // Etiqueta del campo de texto
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible
        )

        // Fila para la casilla de verificación de notificaciones

        Row(
            modifier = Modifier.fillMaxWidth(), // Ocupa todo el ancho disponible
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically // Alinea verticalmente los elementos al centro
        ) {

            // Casilla de verificación para las notificaciones, su estado está determinado por la variable 'notificaciones.value'
            // Cuando se produce un cambio en la casilla de verificación, actualiza el valor de 'notificaciones' con el nuevo estado

            Checkbox(
                checked = notificaciones.value,
                onCheckedChange = { notificaciones.value = it }
            )
            Text(text = "Recibir Notificaciones") // Etiqueta para la casilla de verificación
        }
        Spacer(modifier = Modifier.size(16.dp)) // Espacio en blanco ajustable

        // Botón para enviar el formulario de registro
        // Define un botón composable
        Button(
            // Define la acción que se ejecutará cuando se haga clic en el botón
            onClick = {
                // Llama a la función proporcionada cuando se hace clic en el botón, pasando los valores actuales de los campos de entrada
                onRegisterClicked(
                    nombre.value, // Valor del campo de entrada para el nombre
                    email.value, // Valor del campo de entrada para el correo electrónico
                    password.value, // Valor del campo de entrada para la contraseña
                    pais.value, // Valor del campo de entrada para el país
                    zonaHoraria.value, // Valor del campo de entrada para la zona horaria
                    notificaciones.value // Valor booleano del campo de entrada para las notificaciones
                )
                // Después de registrar, navegar a la pantalla de inicio (HomeScreen)
                    navController.navigate("home") // Asegúrate de tener la ruta "home" definida en tu NavGraph
            },
            // Aplica un modificador para que el botón ocupe todo el ancho disponible en su contenedor
            modifier = Modifier.fillMaxWidth()
        ) {
            // Texto que se mostrará en el botón
            Text("Registrarse")
        }
    }
}
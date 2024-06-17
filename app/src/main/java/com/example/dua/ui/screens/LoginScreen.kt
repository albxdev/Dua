package com.example.dua.ui.screens // la línea package com.example.ui.screens indica que las clases y funciones definidas en este archivo pertenecen al paquete llamado com.example.ui.screens. Esto significa que este archivo forma parte del módulo ui del proyecto, que a su vez está dentro del paquete

import androidx.compose.foundation.BorderStroke // Importación de la clase BorderStroke de Compose para bordes personalizados
import androidx.compose.foundation.Image // Importación de la clase Image de Compose para mostrar imágenes
import androidx.compose.foundation.border // Importación de la función border de Compose para agregar bordes a los elementos
import androidx.compose.foundation.layout.Arrangement // Importación de la clase Arrangement de Compose para manejar la disposición de los elementos
import androidx.compose.foundation.layout.Column // Importación de la clase Column de Compose para organizar elementos verticalmente
import androidx.compose.foundation.layout.Row // Importación de la clase Row de Compose para organizar elementos horizontalmente
import androidx.compose.foundation.layout.Spacer // Importación de la clase Spacer de Compose para agregar espaciado entre elementos
import androidx.compose.foundation.layout.fillMaxSize // Importación de la función fillMaxSize de Compose para ocupar todo el tamaño disponible
import androidx.compose.foundation.layout.fillMaxWidth // Importación de la función fillMaxWidth de Compose para ocupar todo el ancho disponible
import androidx.compose.foundation.layout.height // Importación de la función height de Compose para establecer la altura de un elemento
import androidx.compose.foundation.layout.padding // Importación de la función padding de Compose para agregar relleno a los elementos
import androidx.compose.foundation.layout.size // Importación de la función size de Compose para especificar el tamaño de un elemento
import androidx.compose.material3.Checkbox // Importación de la clase Checkbox de Material3 para seleccionar opciones
import androidx.compose.material3.ElevatedButton // Importación de la clase ElevatedButton de Material3 para botones elevados
import androidx.compose.material3.MaterialTheme // Importación del tema Material3 para mantener la consistencia visual
import androidx.compose.material3.OutlinedTextField // Importación de la clase OutlinedTextField de Material3 para campos de texto con borde
import androidx.compose.material3.Text // Importación de la clase Text de Material3 para mostrar texto
import androidx.compose.material3.TextButton // Importación de la clase TextButton de Material3 para botones de texto
import androidx.compose.material3.TextField // Importación de la clase TextField de Material3 para campos de texto
import androidx.compose.runtime.Composable // Importación de la anotación Composable de Compose para funciones componibles
import androidx.compose.runtime.getValue // Importación de la función getValue de Compose para acceder al valor actual de un estado mutable
import androidx.compose.runtime.mutableStateOf // Importación de la función mutableStateOf de Compose para crear un estado mutable
import androidx.compose.runtime.remember // Importación de la función remember de Compose para mantener el estado entre recomposiciones
import androidx.compose.runtime.setValue // Importación de la función setValue de Compose para actualizar un estado mutable
import androidx.compose.ui.Alignment // Importación de la clase Alignment de Compose para alinear elementos
import androidx.compose.ui.Modifier // Importación de la clase Modifier de Compose para aplicar modificadores a los elementos
import androidx.compose.ui.res.painterResource // Importación de la función painterResource de Compose para cargar recursos de imagen
import androidx.compose.ui.res.stringResource // Importación de la función stringResource de Compose para cargar cadenas de recursos
import androidx.compose.ui.text.input.PasswordVisualTransformation // Importación de la clase PasswordVisualTransformation de Compose para transformar visualmente contraseñas
import androidx.compose.ui.text.input.VisualTransformation // Importación de la clase VisualTransformation de Compose para transformaciones visuales personalizadas
import androidx.compose.ui.unit.dp // Importación de la clase dp de Compose para especificar dimensiones en píxeles independientes de la densidad

import com.example.dua.R // Importación de los recursos definidos en el proyecto

@Composable // Anotación indicando que la función es componible
fun MyImage() { // Definición de la función componible MyImage para mostrar una imagen de avatar
    Image( // Uso de la clase Image para mostrar la imagen de avatar
        painter = painterResource(id = R.drawable.avatar), // Especificación del recurso de imagen mediante painterResource
        contentDescription = "Login Avatar", // Descripción del contenido de la imagen para accesibilidad
        modifier = Modifier // Modificador para ajustar propiedades de la imagen
    )
}

@Composable // Anotación indicando que la función es componible
fun CustomPasswordTextField( // Definición de la función componible CustomPasswordTextField para mostrar un campo de contraseña personalizado
    value: String, // Parámetro para el valor actual del campo de contraseña
    onValueChange: (newPassword: String) -> Unit, // Parámetro para el callback que maneja cambios en el valor del campo de contraseña
    isPasswordVisible: Boolean // Parámetro para indicar si la contraseña es visible o no
) {
    TextField( // Uso de la clase TextField para el campo de contraseña
        value = value, // Valor actual del campo de contraseña
        onValueChange = onValueChange, // Callback para manejar cambios en el valor del campo de contraseña
        label = { Text("Password") }, // Etiqueta para el campo de contraseña
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(), // Transformación visual de la contraseña según su visibilidad
        modifier = Modifier.fillMaxWidth() // Modificador para ajustar el ancho del campo de contraseña
    )
}


@Composable // Indica que esta función es componible
fun LoginScreen( // Definición de la función composable LoginScreen
    onLoginClicked: (String, String) -> Unit, // Callback para el clic en el botón de inicio de sesión
    navigateToRegister: () -> Unit // Callback para navegar a la pantalla de registro
) {
    // Definición de variables para el correo electrónico, contraseña y visibilidad de la contraseña
    var userEmail by remember { mutableStateOf("") } // Estado mutable para el correo electrónico
    var userPassword by remember { mutableStateOf("") } // Estado mutable para la contraseña
    var passwordVisibility by remember { mutableStateOf(false) } // Estado mutable para la visibilidad de la contraseña

    // Columna principal que contiene los elementos de la pantalla de inicio de sesión
    Column(
        modifier = Modifier // Modificador para ajustar propiedades de la columna
            .fillMaxSize() // Ocupa todo el tamaño disponible
            .padding(16.dp), // Agrega relleno alrededor de la columna
        horizontalAlignment = Alignment.CenterHorizontally, // Alineación horizontal al centro
        verticalArrangement = Arrangement.Center // Distribución vertical centrada
    ) {

        MyImage() // Llama a la función composable MyImage para mostrar la imagen de avatar
        // Espaciador para separar la imagen del resto de los elementos

        Spacer(modifier = Modifier.size(120.dp))     // Modificador para establecer el tamaño del espacio vertical

        // Imagen para mostrar el avatar (imagen redonda)
        Image(
            painter = painterResource(id = R.drawable.avatar), // Especifica la imagen del avatar
            contentDescription = stringResource(R.string.login_avatar_content_description), // Descripción para accesibilidad
            modifier = Modifier // Modificador para ajustar propiedades de la imagen
                .size(100.dp) // Tamaño de la imagen
                .border( // Agrega un borde a la imagen
                    BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.secondary) // Ancho y color del borde
                )
        )

        Spacer(modifier = Modifier.height(32.dp)) // Espaciador vertical

        // Campo de entrada para el correo electrónico
        OutlinedTextField(
            value = userEmail, // Valor del campo de correo electrónico
            onValueChange = { userEmail = it }, // Callback para cambios en el campo de correo electrónico
            label = { Text(stringResource(R.string.email_label)) }, // Etiqueta del campo de correo electrónico
            modifier = Modifier.fillMaxWidth() // Modificador para ajustar el ancho del campo
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espaciador vertical

        // Campo de entrada para la contraseña
        CustomPasswordTextField( // Llama a la función composable CustomPasswordTextField
            value = userPassword, // Valor del campo de contraseña
            onValueChange = { newValue -> userPassword = newValue }, // Callback para cambios en la contraseña
            isPasswordVisible = passwordVisibility // Indica si la contraseña es visible o no
        )

        Spacer(modifier = Modifier.height(32.dp)) // Espaciador vertical

        // Fila para mostrar el checkbox de visibilidad de la contraseña
        Row(
            modifier = Modifier.fillMaxWidth(), // Ocupa todo el ancho disponible
            horizontalArrangement = Arrangement.SpaceBetween // Distribución de los elementos
        ) {
            Text(stringResource(R.string.show_password_label)) // Etiqueta para mostrar contraseña
            Checkbox( // Checkbox para controlar la visibilidad de la contraseña
                checked = passwordVisibility, // Estado de la visibilidad de la contraseña
                onCheckedChange = { passwordVisibility = it } // Callback para cambios en la visibilidad
            )
        }

        // Enlace para recuperar contraseña
        TextButton(
            onClick = { /* Maneja la acción de recuperar contraseña */ }, // Acción de clic para recuperar contraseña
            modifier = Modifier // Modificador para ajustar propiedades del botón
                .fillMaxWidth() // Ocupa todo el ancho disponible
                .padding(horizontal = 8.dp) // Agrega relleno horizontal
                .fillMaxWidth() // Ocupa todo el ancho disponible
                .padding(horizontal = 8.dp) // Agrega relleno horizontal
        ) {
            Text(stringResource(R.string.forgot_password_label)) // Texto del botón para recuperar contraseña
        }

        // Enlace para registrarse
        TextButton(
            onClick = { navigateToRegister() }, // Acción de clic para navegar a la pantalla de registro
            modifier = Modifier // Modificador para ajustar propiedades del botón
                .fillMaxWidth() // Ocupa todo el ancho disponible
                .padding(horizontal = 8.dp) // Agrega relleno horizontal
        ) {
            Text(stringResource(R.string.sign_up_label)) // Texto del botón para registrarse
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciador vertical

        // Botón de inicio de sesión
        ElevatedButton(
            onClick = { onLoginClicked(userEmail, userPassword) }, // Acción de clic para iniciar sesión
            modifier = Modifier.fillMaxWidth() // Modificador para ajustar propiedades del botón
        ) {
            Text(stringResource(R.string.login_button_label)) // Texto del botón de inicio de sesión
        }
    }
}
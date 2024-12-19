package com.composse.applibreria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.composse.applibreria.ui.theme.AppLibreriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLibreriaTheme {
                ComicAppUI()
            }
        }
    }
}
@Composable
fun LogoBox() {

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.Gray, shape = CircleShape) // Círculo de fondo
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo), // Reemplaza con el ID de tu imagen
            contentDescription = "App Logo",
            contentScale = ContentScale.Crop, // Ajusta la imagen para que ocupe todo el círculo
            modifier = Modifier.size(40.dp) // Tamaño de la imagen igual al círculo
        )
    }
}
@Composable
fun ComicAppUI() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("settings") { SettingsScreen() }
            composable("logout") { LogoutScreen() }
            composable("home") {
                HomeScreen(navController)
            }
            composable(
                route = "details/{imageId}",
                arguments = listOf(navArgument("imageId") { type = NavType.StringType })
            ) { backStackEntry ->
                val imageId = backStackEntry.arguments?.getString("imageId")
                DetailScreen(imageId)
            }
        }
    }
}
@Composable
fun HomeScreen(navController: NavController) {
    var showMenu by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Gray, shape = CircleShape)
                ){
                    LogoBox()
                }

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Black, shape = CircleShape)
                        .clickable { showMenu = true } // Abre el menú al hacer clic
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_options),
                        contentDescription = "Options",
                        modifier = Modifier.size(40.dp)
                    )

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                showMenu = false
                                navController.navigate("settings")
                            },
                            text = { Text("Configuración") }
                        )
                        DropdownMenuItem(
                            onClick = {
                                showMenu = false
                                navController.navigate("logout")
                            },
                            text = { Text("Salir de sesión") }
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "Mangas",
                fontSize = 24.sp,
                style = TextStyle(

                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
        }
            item {
                // Carrusel de Imágenes para Mangas
                ImageCarousel(navController)
            }

            item {
                // Categoría "Comic"
                Text(
                    text = "Comic",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
            }

            item {
                // Carrusel de Imágenes para Comics
                ImageCarousel(navController)
            }

            item {
                // Categoría "Favoritos"
                Text(
                    text = "Favoritos",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
            }

            item {
                // Carrusel de Imágenes para Favoritos
                ImageCarousel(navController)
            }

            item {
                // Categoría "Marvel"
                Text(
                    text = "Marvel",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
            }

            item {
                // Carrusel de Imágenes para Marvel
                ImageCarousel(navController)
            }

            item {
                // Categoría "DC"
                Text(
                    text = "DC",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
            }

            item {
                // Carrusel de Imágenes para DC
                ImageCarousel(navController)
            }

            item {
                // Categoría "Independientes"
                Text(
                    text = "Independientes",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
            }

            item {
                // Carrusel de Imágenes para Independientes
                ImageCarousel(navController)
            }

            item {
                // Esto sirve para que el contenido restante empuje hacia abajo
                Spacer(modifier = Modifier.height(100.dp)) // Solo para dar más espacio al final
            }
        }
}

@Composable
fun ImageCarousel(navController: NavController) {
    var currentIndex by remember { mutableStateOf(0) }
    val images = (1..10).map { "Image $it" }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentIndex > 0) {
            Box(
                modifier = Modifier
                    .size(24.dp, 150.dp)
                    .background(Color.DarkGray)
                    .clickable { currentIndex-- },
                contentAlignment = Alignment.Center
            ) {
                Text("<", color = Color.White, fontSize = 20.sp)
            }
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            images.subList(currentIndex, (currentIndex + 3).coerceAtMost(images.size)).forEach { image ->
                Box(
                    modifier = Modifier
                        .size(100.dp, 150.dp)
                        .background(Color.LightGray)
                        .clickable {
                            navController.navigate("details/${image}")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = image, color = Color.Black)
                }
            }
        }

        if (currentIndex + 3 < images.size) {
            Box(
                modifier = Modifier
                    .size(24.dp, 150.dp)
                    .background(Color.DarkGray)
                    .clickable { currentIndex++ },
                contentAlignment = Alignment.Center
            ) {
                Text(">", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}
@Composable
fun DetailScreen(imageId: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Detalles de $imageId",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Descripción del $imageId: Esta es una breve descripción de la imagen.",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Enlaces:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items((1..5).toList()) { linkIndex ->
                Text(
                    text = "Enlace $linkIndex para $imageId",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Manejar clic en el enlace */ }
                        .padding(8.dp),
                    color = Color.Blue
                )
            }
        }
    }
}
@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Pantalla de Configuración", fontSize = 20.sp)
        // Opciones de configuración aquí
    }
}

@Composable
fun LogoutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Has salido de la sesión.", fontSize = 20.sp)
    }
}
@Composable
fun BottomNavigationBar() {
    // Un estado para saber cuál icono está seleccionado
    var selectedItem by remember { mutableStateOf(0) }

    // Lista de íconos
    val icons = listOf(
        R.drawable.ic_search,
        R.drawable.ic_favorites,
        R.drawable.ic_profile,
        R.drawable.ic_download,
        R.drawable.ic_upload
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)
            .background(Color.LightGray),

        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Recorremos cada ícono
        icons.forEachIndexed { index, iconRes ->
            IconButton(
                onClick = {
                    // Cambiar el ícono seleccionado
                    selectedItem = index
                }
            ) {
                // Cambiar el color del icono dependiendo de si está seleccionado
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = "Icon $index",
                    modifier = Modifier.size(40.dp),
                    tint = if (selectedItem == index) Color(0xFFFF5722) else Color.Black // Naranja si está seleccionado
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewComicAppUI() {
    ComicAppUI()
}

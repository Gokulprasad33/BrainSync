package com.example.brainsyncapp.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTheme(modifier: Modifier) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(260.dp).background(Color(0xFFFB9B28A)), drawerShape = RectangleShape) {

                Text("BrainSync", modifier = Modifier.padding(20.dp), fontSize = 26.sp, fontWeight = FontWeight.Bold)
                HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp))

                NavigationDrawerItem(
                    label = { Text("Home", fontWeight = FontWeight.SemiBold) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home icon") },
                    selected = currentRoute == "homescreen",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("homescreen") //{ popUpTo("homescreen") { inclusive = true } }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color(0xFFB9B28A),
                        selectedTextColor = Color.White
                    )
                )

                NavigationDrawerItem(
                    label = { Text("Settings", fontWeight = FontWeight.SemiBold) },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings icon") },
                    selected = currentRoute == "settingscreen",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("settingscreen") //{ popUpTo("settingscreen") { inclusive = false } }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color(0xFFB9B28A),
                        selectedTextColor = Color.White
                    )
                )

                NavigationDrawerItem(
                    label = { Text("Theme", fontWeight = FontWeight.SemiBold) },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Theme icon") },
                    selected = currentRoute == "themescreen",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("themescreen") //{ popUpTo("themescreen") { inclusive = false } }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color(0xFFB9B28A),
                        selectedTextColor = Color.White
                    )
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFB9B28A)),
                    title = { Text("BrainSync", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu button")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Navigation(navController)
            }
        }
    }
}
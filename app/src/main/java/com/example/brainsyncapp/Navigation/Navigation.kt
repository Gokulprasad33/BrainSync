package com.example.brainsyncapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.BrainSync.screens.AddNote
import com.example.BrainSync.screens.HomeScreen
import com.example.brainsyncapp.Screens.SettingsScreen
import com.example.brainsyncapp.Screens.ThemesScreen


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen"){
            HomeScreen(navController)
        }
        composable("settingscreen"){
            SettingsScreen(navController)
        }
        composable("themescreen"){
            ThemesScreen(navController)
        }
        composable("addnote"){
            AddNote(navController)
        }
    }
}
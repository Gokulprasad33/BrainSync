package com.example.brainsyncapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.BrainSync.screens.AddNote
import com.example.BrainSync.screens.HomeScreen
import com.example.BrainSync.screens.ViewNote
import com.example.brainsyncapp.Screens.About


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen"){
            HomeScreen(navController)
        }
//        composable("settingscreen"){
//            SettingsScreen(navController)
//        }
        composable("addnote"){
            AddNote(navController)
        }
        composable("aboutscreen"){
            About(navController)
        }
        composable("viewnote/{noteId}", arguments = listOf(
            navArgument("noteId") { type = NavType.IntType }
        )) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            ViewNote(navController, noteId)
        }
    }
}
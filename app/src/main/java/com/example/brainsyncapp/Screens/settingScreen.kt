//package com.example.brainsyncapp.Screens
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Button
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Switch
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.example.brainsyncapp.viewmodel.NoteViewModel
//
//@Composable
//fun SettingsScreen(navController: NavHostController,noteViewModel: NoteViewModel) {
//    Box(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.secondary)
//    ) {
//        var isDarkTheme by remember { mutableStateOf(false) }
//        var fontSize by remember { mutableStateOf("Medium") }
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Theme Switch
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text("Theme", fontSize = 30.sp, color = Color.Black, fontWeight = FontWeight.Medium)
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row {
//                var dropdownMenuStatus by remember { mutableStateOf(false) }
//                Text("Choose Theme", fontSize = 22.sp, color = Color.Black)
//                DropdownMenu(
//                    expanded = dropdownMenuStatus,
//                    onDismissRequest = { dropdownMenuStatus = false }
//                ) {
//                    DropdownMenuItem(
//                        text = { Text("Dynamic theme") },
//                        onClick = {
//                            noteViewModel.getTheme
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
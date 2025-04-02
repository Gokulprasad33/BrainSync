package com.example.brainsyncapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.brainsyncapp.Navigation.MainTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.brainsyncapp.database.NoteDatabase
import com.example.brainsyncapp.database.NoteEntity
import com.example.brainsyncapp.ui.theme.BrainSyncAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrainSyncAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainTheme(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

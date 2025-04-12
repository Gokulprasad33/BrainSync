package com.example.brainsyncapp.Screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.brainsyncapp.R
import java.nio.file.WatchEvent

@Composable
fun AboutPage(navController: NavController) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        val context = LocalContext.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // GitHub IconButton
            IconButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Gokulprasad33/BrainSync"))
                    context.startActivity(intent)
                },
                modifier = Modifier.size(250.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_transparent),
                    contentDescription = "GitHub Link",
                    modifier = Modifier.size(250.dp),
                    tint = Color.Unspecified
                )
            }
            Text(
                text = "BrainSync",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 30.sp,
            )

            Row {
                Text(
                    text = "0.1 | Beta",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 20.sp,
                )
            }
            Text(
                text = "By Gokul prasad",
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 20.sp,
            )
        }
    }
}
package com.jerbuchameeris.aviajet.ui.components

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jerbuchameeris.aviajet.R

@Composable
fun MainMenuView(points: Int, onNewGame: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BackgroundImage(painterResource(R.drawable.background))

        Box(modifier = Modifier.padding(24.dp)) {
            //PointsView(points = points, modifier = Modifier.align(Alignment.TopEnd))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ImageButton(
                normal = painterResource(id = R.drawable.normal_button_play),
                pressed = painterResource(R.drawable.pressed_button_play),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxSize()
            ) {
                onNewGame()
            }
        }
        
    }
}

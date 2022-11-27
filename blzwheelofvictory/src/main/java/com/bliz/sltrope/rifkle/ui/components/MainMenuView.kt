package com.bliz.sltrope.rifkle.ui.components

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
import com.bliz.sltrope.rifkle.R

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
                normal = painterResource(id = R.drawable.normal_button_new),
                pressed = painterResource(R.drawable.pressed_button_new),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxSize()
            ) {
                onNewGame()
            }

            ImageButton(
                normal = painterResource(id = R.drawable.normal_button_continue),
                pressed = painterResource(R.drawable.pressed_button_continue),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxSize()
            ) {
                onNewGame()
            }

            Spacer(modifier = Modifier.height(10.dp))

            val activity = LocalContext.current as Activity
            ImageButton(
                normal = painterResource(id = R.drawable.normal_button_exit),
                pressed = painterResource(R.drawable.pressed_button_exit),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxSize()
            ) {
                activity.finish()
            }
        }
        
    }
}

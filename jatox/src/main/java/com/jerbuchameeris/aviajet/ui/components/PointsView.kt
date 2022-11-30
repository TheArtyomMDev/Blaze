package com.jerbuchameeris.aviajet.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PointsView(
    points: Int,
    modifier: Modifier
) {
    
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "SCORE: ",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
        Text(
            text = points.toString(),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
        /*
        Image(
            painter = painterResource(R.drawable.icon_score),
            contentDescription = null,
            modifier = Modifier
                .height(15.dp)
                .width(20.dp)
                .fillMaxSize()
        )
         */
    }
}
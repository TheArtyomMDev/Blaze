package com.slblzoctoplaymgte.opeir.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.slblzoctoplaymgte.opeir.R

@Composable
fun LoadingView(progress: Float) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BackgroundImage(painter = painterResource(R.drawable.background))
        Box {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(100.dp)
                    .height(100.dp)
                    //.wrapContentHeight()
                    //.padding(horizontal = 48.dp)
                ,
                //contentScale = ContentScale.Fit,
            )
            ProgressBar(
                emptyImage = painterResource(R.drawable.loading_bar),
                fullImage = painterResource(R.drawable.loading_fill),
                progress = progress,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 76.dp)
                    .padding(bottom = 76.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            )
            /*
            Image(
                painter = painterResource(R.drawable.full_loading_bar),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 76.dp)
                    .padding(bottom = 76.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )

             */
        }
    }
}
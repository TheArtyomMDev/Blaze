package com.avijekrl.proald.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    emptyImage: Painter,
    fullImage: Painter,
    progress: Float = 1.0F,
    modifier: Modifier
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    Box(modifier = modifier.onSizeChanged {
        size = it
    }) {
        Image(
            painter = emptyImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                //.matchParentSize()
            ,
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = fullImage,
            contentDescription = null,
            modifier = Modifier
                //.fillMaxSize()
                //.matchParentSize()
                .fillMaxHeight()
                //.height(40.dp)
                .width(
                    with(LocalDensity.current) {
                        (size.width.toDp() * progress)
                    }
                )
                .padding(5.dp)
            ,
            contentScale = ContentScale.FillBounds
        )
    }
}
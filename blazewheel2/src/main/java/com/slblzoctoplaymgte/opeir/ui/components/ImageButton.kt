package com.slblzoctoplaymgte.opeir.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageButton(
    normal: Painter,
    pressed: Painter,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Image(
        painter = if (!isPressed) normal else pressed,
        contentDescription = null,
        modifier = modifier
            .combinedClickable(
                interactionSource = interactionSource,
                null,
                onClick = {
                    onClick()
                }
            )
    )
}
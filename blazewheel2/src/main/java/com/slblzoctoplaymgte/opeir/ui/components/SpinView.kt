package com.slblzoctoplaymgte.opeir.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.slblzoctoplaymgte.opeir.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpinView(
    points: Int,
    onHomeClick: () -> Unit,
    doOnClick: (isCorrect: Boolean) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var clicked by remember {
            mutableStateOf(false)
        }
        val scope = rememberCoroutineScope()
        val allIds = listOf(
            R.drawable.slot_1,
            R.drawable.slot_2,
            R.drawable.slot_3,
            R.drawable.slot_4,
            R.drawable.slot_5,
            R.drawable.slot_6
        )

        var picturesIds by remember {
            mutableStateOf((1..12).map { allIds.random() })
        }

        LaunchedEffect(key1 = clicked ) {
            if(clicked) {
                for(i in 1..50) {
                    picturesIds = (1..12).map { allIds.random() }
                    delay(100)
                }
            }
        }

        val pictures = picturesIds.map { painterResource(it) }

        BackgroundImage(painterResource(R.drawable.background))
        Column {
            Row() {
                SideBar(
                    Modifier
                        .padding(top = 20.dp)
                        .width(100.dp)
                        .wrapContentHeight()
                ) {
                    onHomeClick()
                }
                Spacer(Modifier.width(20.dp))
                Box {
                    Image(
                        painter = painterResource(R.drawable.slot_frame),
                        contentDescription = null
                    )
                    LazyVerticalGrid(
                        GridCells.Fixed(4),
                        modifier = Modifier.matchParentSize(),
                        contentPadding = PaddingValues(vertical = 30.dp, horizontal = 30.dp)
                    ) {
                        items(pictures) { painter ->
                            //Spacer(Modifier.height(50.dp))
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                                    .height(75.dp)
                                    .width(75.dp)
                            )
                            //Spacer(Modifier.height(20.dp))
                        }
                    }
                }
                
                Image(
                    painter = painterResource(if(clicked) R.drawable.lever_down else R.drawable.lever_up),
                    contentDescription = null,
                    modifier = Modifier
                        .align(if(clicked) Alignment.Bottom else Alignment.Top)
                        .clickable {
                            clicked = true
                            scope.launch {
                                delay(5000)
                                clicked = false
                            }
                        }
                )
            }
            Row {
                Spacer(Modifier.width(100.dp))
                Box {
                    Image(
                        painter = painterResource(R.drawable.win_frame),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(200.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "WIN : 87000",

                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                            .padding(bottom = 10.dp)
                        ,
                        textAlign = TextAlign.Center
                    )
                }
                Box {
                    Image(
                        painter = painterResource(R.drawable.win_frame),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(150.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "BIN : 800",

                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                            .padding(bottom = 10.dp)
                        ,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.width(100.dp))
                ImageButton(
                    normal = painterResource(R.drawable.normal_button_auto),
                    pressed = painterResource(R.drawable.normal_button_auto_1),
                    modifier = Modifier.fillMaxHeight()
                ) {}
            }
        }
    }
}

@Composable
fun SideBar(modifier: Modifier, onHomeClick: () -> Unit) {
    Column(modifier = modifier) {
        ImageButton(
            normal = painterResource(R.drawable.normal_button_close),
            pressed = painterResource(R.drawable.pressed_button_close),
            modifier = Modifier.fillMaxWidth()
        ) {
            onHomeClick()
        }

        ImageButton(
            normal = painterResource(R.drawable.normal_button_sound_on),
            pressed = painterResource(R.drawable.pressed_button_sound_on),
            modifier = Modifier.fillMaxWidth()
        ) {}

        ImageButton(
            normal = painterResource(R.drawable.normal_button_share),
            pressed = painterResource(R.drawable.pressed_button_share),
            modifier = Modifier.fillMaxWidth()
        ) {}
    }
}


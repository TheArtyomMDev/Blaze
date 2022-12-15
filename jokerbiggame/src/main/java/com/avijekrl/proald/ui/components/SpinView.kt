package com.avijekrl.proald.ui.components

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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avijekrl.proald.R
import com.avijekrl.proald.ui.theme.UniQuizTheme
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
        )

        var picturesIds by remember {
            mutableStateOf((1..9).map { allIds.random() })
        }

        LaunchedEffect(key1 = clicked ) {
            if(clicked) {
                for(i in 1..50) {
                    picturesIds = (1..9).map { allIds.random() }
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
                Spacer(Modifier.width(120.dp))
                Box(
                    Modifier
                        .height(280.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.slot_frame),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                    )
                    LazyVerticalGrid(
                        GridCells.Fixed(3),
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 23.dp)
                        ,
                        contentPadding = PaddingValues(vertical = 30.dp, horizontal = 13.dp)
                    ) {
                        items(pictures) { painter ->
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(bottom = 5.dp, start = 5.dp, end = 0.dp)
                                    .height(55.dp)
                                    .width(50.dp)
                            )
                        }
                    }
                }
            }

            Row(Modifier.offset(y = (-40).dp)) {
                Spacer(Modifier.width(150.dp))

                Box {
                    Image(
                        painter = painterResource(R.drawable.win_frame),
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(150.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "WIN 39.088",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                            .padding(bottom = 10.dp)
                        ,
                        textAlign = TextAlign.Center
                    )
                }

                ImageButton(
                    normal = painterResource(R.drawable.normal_button_auto),
                    pressed = painterResource(R.drawable.pressed_button_auto),
                    modifier = Modifier.offset(x = (-15).dp)
                ) {
                    clicked = true
                    scope.launch {
                        delay(5000)
                        clicked = false
                    }
                }

                ImageButton(
                    normal = painterResource(R.drawable.normal_button_play),
                    pressed = painterResource(R.drawable.pressed_button_play),
                    modifier = Modifier.offset(x = (-25).dp)
                ) {
                    clicked = true
                    scope.launch {
                        delay(5000)
                        clicked = false
                    }
                }

                ImageButton(
                    normal = painterResource(R.drawable.normal_button_max),
                    pressed = painterResource(R.drawable.pressed_button_max),
                    modifier = Modifier.offset(x = (-35).dp)
                ) {
                    clicked = true
                    scope.launch {
                        delay(5000)
                        clicked = false
                    }
                }

                Box {
                    Image(
                        painter = painterResource(R.drawable.win_frame),
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(100.dp)
                            .offset(x = (-45).dp)
                        ,
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "BT 300",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                            .padding(bottom = 10.dp)
                            .offset(x = (-45).dp)
                        ,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun SideBar(modifier: Modifier, onHomeClick: () -> Unit) {
    Column(modifier = modifier) {

        ImageButton(
            normal = painterResource(R.drawable.normal_button_menu),
            pressed = painterResource(R.drawable.pressed_button_menu),
            modifier = Modifier.fillMaxWidth()
        ) {
            onHomeClick()
        }
    }
}

@Preview(showBackground = true, widthDp = 600, heightDp = 280)
@Composable
fun DefaultPreview() {
    UniQuizTheme {
        //ChoosingView()
        SpinView(0, {}, {})
        //QuestionView(Question("Default question"), QuizTopic.Players) {}
    }
}
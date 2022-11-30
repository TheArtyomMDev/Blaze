package com.jerbuchameeris.aviajet.ui.components

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
import com.jerbuchameeris.aviajet.R
import com.jerbuchameeris.aviajet.ui.theme.UniQuizTheme
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
                Box(Modifier.height(230.dp)) {
                    Image(
                        painter = painterResource(R.drawable.slot_frame),
                        contentDescription = null,
                        modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                    )
                    LazyVerticalGrid(
                        GridCells.Fixed(4),
                        modifier = Modifier.matchParentSize(),
                        contentPadding = PaddingValues(vertical = 30.dp, horizontal = 10.dp)
                    ) {
                        items(pictures) { painter ->
                            //Spacer(Modifier.height(50.dp))
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(bottom = 10.dp, start = 15.dp, end = 0.dp)
                                    .height(55.dp)
                                    .width(55.dp)
                            )
                            //Spacer(Modifier.height(20.dp))
                        }
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Row {
                Spacer(Modifier.width(170.dp))

                ImageButton(
                    normal = painterResource(R.drawable.normal_button_plus),
                    pressed = painterResource(R.drawable.pressed_button_plus),
                    modifier = Modifier
                        .padding(top = 10.dp,end = 10.dp)
                        .height(20.dp)
                        .width(20.dp)
                ) {}


                Box {
                    Image(
                        painter = painterResource(R.drawable.win_frame),
                        contentDescription = null,
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "bt 100",

                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                            .padding(bottom = 10.dp)
                        ,
                        textAlign = TextAlign.Center
                    )
                }

                ImageButton(
                    normal = painterResource(R.drawable.normal_button_minus),
                    pressed = painterResource(R.drawable.pressed_button_minus),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .height(20.dp)
                        .width(20.dp)
                ) {}

                Spacer(Modifier.width(10.dp))

                Box {
                    Image(
                        painter = painterResource(R.drawable.win_frame),
                        contentDescription = null,
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "WIN 20 000",

                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                            .padding(bottom = 10.dp)
                        ,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.width(50.dp))
                ImageButton(
                    normal = painterResource(R.drawable.normal_button_start),
                    pressed = painterResource(R.drawable.pressed_button_start),
                    modifier = Modifier
                        .width(200.dp)
                ) {
                    clicked = true
                    scope.launch {
                        delay(5000)
                        clicked = false
                    }
                }
            }
        }
    }
}

@Composable
fun SideBar(modifier: Modifier, onHomeClick: () -> Unit) {
    Column(modifier = modifier) {
        val soundButtonClicked = remember {
            mutableStateOf(false)
        }

        val picture = if( !(soundButtonClicked.value))
           painterResource(R.drawable.normal_button_sound_on)
        else
            painterResource(R.drawable.normal_button_sound_off)

        ImageButton(
            normal = picture,
            pressed = picture,
            modifier = Modifier.fillMaxWidth()
        ) {
            soundButtonClicked.value = !(soundButtonClicked.value)
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
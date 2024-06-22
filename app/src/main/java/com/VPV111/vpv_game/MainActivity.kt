package com.VPV111.vpv_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.VPV111.vpv_game.ui.theme.VPV111_GameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        updateContent();
    }

    private fun updateContent(){
        setContent {
            VPV111_GameTheme {
                val (clickCount, setClickCount) = remember { mutableIntStateOf(0) }
                val (progress, setProgress) = remember { mutableFloatStateOf(0.5f) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        DrawTopStatus(Modifier, clickCount)
                    }
                ) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        clickCount, setClickCount, progress, setProgress)
                }
            }
        }

    }
}

@Composable
fun DrawBackgroundImage(modifier: Modifier = Modifier){
    Image(painterResource(
        id = R.drawable.idle),
        null,
        modifier = modifier.fillMaxSize(),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop)
}

@Composable
fun DrawTopStatus(modifier: Modifier = Modifier, clickCount: Int){
    Box(contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .background(color = Color.Transparent)) {
        Image(painterResource(id = R.drawable.idle),
            null,
            modifier = modifier
                .fillMaxWidth()
                .blur(Dp(10f))
                .alpha(0.45f)
                .height(Dp(50f)),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop)
        Text(text = "Было нажато $clickCount раз",
            color = Color.Cyan,
            fontSize = TextUnit(7f, TextUnitType.Em),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun DrawButtons(modifier: Modifier = Modifier,
                clickCount: Int, setClickCount: (Int)->Unit){
    Column( modifier = Modifier
        .height(200.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround) {
        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                //.height(60.dp)
                .background(color = Color.Transparent)
                //.background(color = Color.Cyan)
        ){
            TextButton(
                modifier = Modifier
                    .align(Alignment.Center)
                    //.height(50.dp)
                    //.requiredHeight(60.dp)
                    //.wrapContentHeight()
                    .background(color = Color.Magenta, shape = RoundedCornerShape(50)),
                onClick = { setClickCount(clickCount + 1) }
            ) {
                Text(text = "Просить милостыню (бесплатно)",
                    fontSize = TextUnit(5f, TextUnitType.Em))
            }

        }
        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                //.height(60.dp)
                .background(color = Color.Transparent)
                //.background(color = Color.DarkGray)
        ){
            TextButton(
                modifier = Modifier
                    .align(Alignment.Center)
                    //.requiredHeight(60.dp)
                    //.wrapContentHeight()
                    .background(color = Color.Magenta, shape = RoundedCornerShape(50)),
                onClick = { setClickCount(clickCount + 1) }
            ) {
                Text(text = "Построить платный туалет 100 Руб",
                    fontSize = TextUnit(5f, TextUnitType.Em))
            }

        }
    }
}

@Composable
fun DrawProgressBar( progress: Float, setProgress: (Float)->Unit = {}){
    Box(
        modifier = Modifier.fillMaxWidth().height(50.dp)
    ){
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            color = Color.Magenta,
            trackColor = Color(115,81,132),
            strokeCap = StrokeCap.Round)
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier,
             clickCount: Int, setClickCount: (Int)->Unit = {},
             progress: Float, setProgress: (Float)->Unit = {},) {
    DrawBackgroundImage(Modifier);
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        //DrawTopStatus(modifier, clickCount)
        DrawProgressBar(progress, setProgress)
        DrawButtons(modifier, clickCount, setClickCount);
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VPV111_GameTheme {
        val (clickCount, setClickCount) = remember { mutableIntStateOf(333) }
        val (progress, setProgress) = remember { mutableFloatStateOf(0.5f) }
        Greeting(clickCount = clickCount, setClickCount = setClickCount,
            progress = progress, setProgress = setProgress)
        DrawTopStatus(clickCount = clickCount);
    }
}
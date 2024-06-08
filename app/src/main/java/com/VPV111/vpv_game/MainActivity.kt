package com.VPV111.vpv_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))
                }
            }
        }

    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Image(painterResource(
        id = R.drawable.idle),
        null,
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop)
    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()) {
        val (clickCount, setClickCount) = remember { mutableIntStateOf(0) }
        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)) {
            Image(painterResource(id = R.drawable.idle),
                null,
                modifier = Modifier
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
        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize().background(color = Color.Transparent)){
            TextButton(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(color = Color.Magenta, shape = RoundedCornerShape(50)),
                onClick = { setClickCount(clickCount + 1) }
            ) {
                Text(text = "Нажми менЯЯЯЯЯЯЯЯЯЯ!!!!",
                    fontSize = TextUnit(5f, TextUnitType.Em))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VPV111_GameTheme {
        Greeting()
    }
}
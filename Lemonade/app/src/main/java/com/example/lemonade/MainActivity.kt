package com.example.lemonade

import android.content.res.Resources.Theme
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun LemonadeApp() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Header()
        LemonContent()
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Lemonade", fontWeight = FontWeight(700))
    }
}

@Composable
fun LemonContent() {
    var step by remember { mutableIntStateOf(0) }
    val text = getStepText(step)
    val image = getStepImage(step)
    val alt = getStepAlt(step)

    fun updateStep() {
        step = (step + 1) % 4
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.clickable { updateStep() },
            painter = painterResource(id = image),
            contentDescription = alt
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text)
    }
}

fun getStepImage(step: Int): Int {
    return when (step) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
}

fun getStepAlt(step: Int): String {
    return when (step) {
        0 -> "lemon tree"
        1 -> "lemon squeeze"
        2 -> "lemon drink"
        else -> "lemon restart"
    }
}

fun getStepText(step: Int): String {
    return when (step) {
        0 -> "Tap the lemon tree to select a lemon"
        1 -> "Keep tapping the lemon to squeeze it"
        2 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }
}

package com.example.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                TipCalculatorApp()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TipCalculatorApp() {
    var billAmount by remember { mutableStateOf("0.00") }
    val tipAmount = calculateTip(billAmount.toDouble(), 15.0)

    Surface() {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.calculate_tip), fontSize = 30.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(R.string.tip_amount, tipAmount))
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = billAmount,
                onValueChange = { billAmount = it },
                label = { Text(stringResource(id = R.string.bill_amount )) })
            Text(billAmount)
        }
    }
}

fun calculateTip(amount: Double, tipPercent: Double): String {
    val tip = amount * tipPercent / 100
    return NumberFormat.getCurrencyInstance().format(tip)
}
package com.example.fitmeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitmeter.ui.theme.FitMeterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitMeterTheme {
               Surface(modifier = Modifier.fillMaxSize()) {
                   BMICalculatorScreen()
               }
            }
        }
    }
}

@Composable
fun BMICalculatorScreen() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy((16.dp))
    ) {
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (kg)")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            val w = weight.toFloatOrNull()
            val h = height.toFloatOrNull()


            result = if (w != null && h != null && h > 0) {
                val hM = h / 100
                val bmi = w / (hM * hM)
                val category = when {
                    bmi < 18.5 -> "Underweight"
                    bmi < 24.9 -> "Normal weight"
                    bmi < 29.9 -> "Overweight"
                    else -> "Obesity"
                }
                "BMI: %.2f\nCategory: %s".format(bmi, category)
            } else {
                "Please enter valid numbers"
            }

        }) {
            Text("Calcuate BMI")
        }

        Text (text = result)
    }

}

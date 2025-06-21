package com.example.fitmeter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightInput = findViewById<EditText>(R.id.weightInput)
        val heightInput = findViewById<EditText>(R.id.heightInput)
        val resultText = findViewById<TextView>(R.id.resultText)
        val calculateBtn = findViewById<Button>(R.id.calculateBtn)

        calculateBtn.setOnClickListener {
            val weight = weightInput.text.toString().toFloatOrNull()
            val heightCm = heightInput.text.toString().toFloatOrNull()

            if (weight != null && heightCm != null && heightCm > 0) {
                val heightM = heightCm / 100
                val bmi = weight / (heightM * heightM)
                val category = when {
                    bmi < 18.5 -> "Underweight"
                    bmi < 24.9 -> "Normal weight"
                    bmi < 29.9 -> "Overweight"
                    else -> "Obesity"
                }
                resultText.text = "BMI: %.2f\nCategory: %s".format(bmi, category)
            } else {
                resultText.text = "Please enter valid values"
            }
        }
    }
}

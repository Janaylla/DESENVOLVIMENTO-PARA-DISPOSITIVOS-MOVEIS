package com.example.classlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var num1EditText: EditText
    private lateinit var num2EditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1EditText = findViewById(R.id.num1EditText)
        num2EditText = findViewById(R.id.num2EditText)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            calculateSum()
        }
    }
    private fun calculateSum() {
        val num1Str = num1EditText.text.toString()
        val num2Str = num2EditText.text.toString()

        if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
            val num1 = num1Str.toDouble()
            val num2 = num2Str.toDouble()

            val sum = num1 + num2

            resultTextView.text = "Resultado: $sum"
            Log.i("Soma: ","$num1 + $num2 = $sum")
        } else {
            resultTextView.text = "Preencha ambos os números"
        }
    }
}
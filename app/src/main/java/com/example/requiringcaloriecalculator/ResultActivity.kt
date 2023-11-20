package com.example.requiringcaloriecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import kotlin.math.*

class ResultActivity : AppCompatActivity() {

    private lateinit var descriptionText: TextView
    private lateinit var resultText: TextView
    private lateinit var carbohydratesDataText: TextView
    private lateinit var proteinDataText: TextView
    private lateinit var fatDataText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        descriptionText = findViewById(R.id.description_text)
        resultText = findViewById(R.id.result_text)
        carbohydratesDataText = findViewById(R.id.carbohydratesData_text)
        proteinDataText = findViewById(R.id.proteinData_text)
        fatDataText = findViewById(R.id.fatData_text)

        val height = intent.getIntExtra("height", 0)
        val weight = intent.getDoubleExtra("weight", 0.0)
        val gender = intent.getIntExtra("gender", 0)


        var standardWeight = if (gender == 1) (height / 100.0).pow(2.0) * 22 else (height / 100.0).pow(2.0) * 21
        standardWeight = round(standardWeight * 10) / 10

        displayInfo(1, standardWeight, calculate(standardWeight))

        val changeToggleBtn: ToggleButton = findViewById(R.id.change_toggleBtn)
        val backBtn: Button = findViewById(R.id.back_btn)

        changeToggleBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                displayInfo(0, weight, calculate(weight))
            } else {
                displayInfo(1, standardWeight, calculate(standardWeight))
            }
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun calculate(weight: Double): List<Int> {
        val standardCalorie = (weight * 30).roundToInt()
        val requireCarbohydrates = (standardCalorie * 0.45).roundToInt()
        val requireProtein = (standardCalorie * 0.30).roundToInt()
        val requireFat = (standardCalorie * 0.25).roundToInt()

        return listOf(standardCalorie, requireCarbohydrates, requireProtein, requireFat)
    }

    private fun displayInfo(type: Int, weight: Double, result: List<Int>) {
        if (type == 0) {
            descriptionText.text = "현재체중인 ${weight}으로 봤을 때, 하루필요열량은"
            resultText.text = "${result[0]}kcal"
            carbohydratesDataText.text = "${result[1]}g"
            proteinDataText.text = "${result[2]}g"
            fatDataText.text = "${result[3]}g"
        } else {
            descriptionText.text = "표준체중인 ${weight}으로 봤을 때, 하루필요열량은"
            resultText.text = "${result[0]}kcal"
            carbohydratesDataText.text = "${result[1]}g"
            proteinDataText.text = "${result[2]}g"
            fatDataText.text = "${result[3]}g"
        }

    }
}
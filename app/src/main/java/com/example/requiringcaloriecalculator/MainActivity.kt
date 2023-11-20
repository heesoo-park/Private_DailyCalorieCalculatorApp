package com.example.requiringcaloriecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightEdit: EditText = findViewById(R.id.height_edit)
        val weightEdit: EditText = findViewById(R.id.weight_edit)
        val radioGroup: RadioGroup = findViewById(R.id.radio_group)
        val calculateBtn: Button = findViewById(R.id.calculate_btn)

        var gender: Int = 0

        radioGroup.setOnCheckedChangeListener { radioGroup, type ->
            when(type) {
                R.id.man_radioBtn -> gender = 1
                R.id.woman_radioBtn -> gender = 2
            }
        }

        calculateBtn.setOnClickListener {
            if (heightEdit.text.isEmpty()) {
                Toast.makeText(this, "신장을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (weightEdit.text.isEmpty()) {
                Toast.makeText(this, "체중을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (gender == 0) {
                Toast.makeText(this, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val height = heightEdit.text.toString().toInt()
            val weight = weightEdit.text.toString().toDouble()



            val intent: Intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("gender", gender)

            startActivity(intent)
        }
    }
}
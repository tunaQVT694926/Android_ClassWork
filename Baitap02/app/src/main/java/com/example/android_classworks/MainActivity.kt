package com.example.android_classworks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_classworks.ui.theme.Android_ClassWorksTheme

import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linear_layout)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString().trim()
            if (input.isEmpty() || !input.matches("\\d+".toRegex())) {
                textViewError.text = "Vui lòng nhập số nguyên dương hợp lệ."
                listView.adapter = null
                return@setOnClickListener
            }

            val n = input.toInt()
            val results = arrayListOf<Int>()
            val selectedId = radioGroup.checkedRadioButtonId

            when (selectedId) {
                R.id.radioEven -> {
                    for (i in 0..n step 2) {
                        results.add(i)
                    }
                }
                R.id.radioOdd -> {
                    for (i in 1..n step 2) {
                        results.add(i)
                    }
                }
                R.id.radioSquare -> {
                    var i = 0
                    while (i * i <= n) {
                        results.add(i * i)
                        i++
                    }
                }
            }

            if (results.isEmpty()) {
                textViewError.text = "Không có số phù hợp."
            } else {
                textViewError.text = ""
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listView.adapter = adapter
        }
    }
}
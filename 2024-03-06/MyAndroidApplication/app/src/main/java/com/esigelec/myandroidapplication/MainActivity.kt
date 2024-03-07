package com.esigelec.myandroidapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(
            R.id.textView)
        textView.text = "olá"
        val editText = findViewById<EditText>(R.id.editText)
        editText.setText("0")
    }
    fun buttonPressed(view:View){
        Toast.makeText(this,"Tchau",Toast.LENGTH_LONG).show()
        var num = findViewById<EditText>(R.id.editText).text.toString().toInt()
        num++
        findViewById<EditText>(R.id.editText).setText(""+num)
    }

}
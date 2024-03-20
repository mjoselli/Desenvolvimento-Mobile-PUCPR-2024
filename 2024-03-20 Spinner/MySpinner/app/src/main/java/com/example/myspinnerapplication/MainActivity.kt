package com.example.myspinnerapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myspinnerapplication.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val itens = listOf("home","work","OOO","PTO","coffee")
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, itens).apply{
              setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@MainActivity,
                    "Selected: ${itens[position]}",
                    Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.saveButton.setOnClickListener{
            val text = binding.editText.text.toString()
            saveContentToFile(this,text,"texto.txt")
            //getString(R.string.save_msg)
            Toast
                .makeText(this,R.string.save_msg,Toast.LENGTH_LONG)
                .show()
        }

        binding.loadButton.setOnClickListener {
            val text = loadContentFromFile(this,"texto.txt")
            Toast.makeText(this,text,Toast.LENGTH_LONG).show()
        }
    }
    private fun loadContentFromFile(context: Context, filename: String) :String?{
        return try {
            context.openFileInput(filename).use {inputStream ->
                inputStream.bufferedReader().use {reader ->
                    reader.readText()
                }
            }
        }catch (e: IOException){
            null
        }
    }
    private fun saveContentToFile(context: Context, content: String, filename: String){
        context.openFileOutput(filename,Context.MODE_PRIVATE).use {
            it.write(content.toByteArray())
        }
    }
}
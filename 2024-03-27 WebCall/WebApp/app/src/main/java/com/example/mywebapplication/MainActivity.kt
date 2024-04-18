package com.example.mywebapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mywebapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(ChuckNorrisAPI::class.java)
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = api.getRandomJoke()
                binding.textView.text = response.value
            }
        }
    }
}
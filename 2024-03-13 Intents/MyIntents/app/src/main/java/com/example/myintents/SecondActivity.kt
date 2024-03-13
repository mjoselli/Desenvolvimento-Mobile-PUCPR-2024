package com.example.myintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myintents.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySecondBinding>(
            this,
            R.layout.activity_second)
        val counterText = intent.getStringExtra("information")
        binding.secondTextView.text = counterText

        binding.sendButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("second",binding.secondEditText.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}
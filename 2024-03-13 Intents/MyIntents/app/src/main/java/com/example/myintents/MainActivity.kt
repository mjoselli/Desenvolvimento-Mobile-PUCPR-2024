package com.example.myintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.myintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var counter = 0

    val resultLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val result = it.data?.getStringExtra("second")
            binding.textView.text = result
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.textView).apply {
            text = "Olá"
        }*/


        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,R.layout.activity_main)
        binding.textView.text = "text"

        binding.button3.setOnClickListener {

        }

        binding.goToSecondActivityButton.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            counter++
            intent.putExtra("information","clickec: $counter")

        //startActivity(intent)
        }

        Log.d("CoolIntents","onCreate")
    }



    fun onClicked(view: View){
        Toast
            .makeText(this,"button clicked",Toast.LENGTH_LONG)
            .show()
        Log.d("CoolIntents","view")
    }


    override fun onStart() {
        super.onStart()
        Log.wtf("CoolIntents","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("CoolIntents","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w("CoolIntents","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("CoolIntents","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("CoolIntents","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("CoolIntents","onRestart")
    }
}
package com.example.myrecyclerviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecyclerviewapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main)
        binding.mainRecyclerView.adapter =
            CityAdapter(object : CityAdapter.OnCityClickListener{
                override fun onCityClick(view: View, position: Int) {
                    Singleton.cities[position].apply {
                        name += " Clicked"
                    }
                    binding.
                    mainRecyclerView.
                    adapter?.notifyItemChanged(position)
                }

                override fun onCityLongClick(view: View, position: Int) {
                    Singleton.cities.removeAt(position)
                    binding.
                    mainRecyclerView.
                    adapter?.notifyItemRemoved(position)
                }
            })

        for (i in 0..10){
            Singleton.cities.add(
                City("City $i",i)
            )
        }
        binding.mainRecyclerView.layoutManager =
            LinearLayoutManager(this)
        //binding.mainRecyclerView.setOn
        binding.addButton.setOnClickListener {
            Singleton.cities.add(
               City("City Added",1000)
            )
            binding.mainRecyclerView.adapter?.notifyItemInserted(
                Singleton.cities.size - 1
            )
        }
    }
}
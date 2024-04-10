package com.example.myrecyclerviewapplication

import android.content.Intent
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
                    Singleton.citySelected = position
                    val intent = Intent(this@MainActivity,
                        CityDetailsActivity::class.java)
                    startActivity(intent)
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

        binding.addButton.setOnClickListener {
            Singleton.citySelected = -1
            val intent = Intent(this,
                CityDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mainRecyclerView.adapter?.
                notifyDataSetChanged()
    }
}
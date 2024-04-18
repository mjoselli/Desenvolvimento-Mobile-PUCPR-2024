package com.example.myrecyclerviewapplication.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecyclerviewapplication.R
import com.example.myrecyclerviewapplication.databinding.ActivityMainBinding
import com.example.myrecyclerviewapplication.model.Singleton
import com.example.myrecyclerviewapplication.viewmodel.MainViewModel
import com.example.myrecyclerviewapplication.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        Singleton.setContext(this)
        viewModel = MainViewModelFactory().create(MainViewModel::class.java)
        viewModel.citiesLiveData.observe(this){
            binding.mainRecyclerView.adapter?.notifyDataSetChanged()
        }
        binding.mainRecyclerView.adapter =
            CityAdapter(object : CityAdapter.OnCityClickListener {
                override fun onCityClick(view: View, position: Int) {
                    Singleton.citySelected = Singleton.cities[position]
                    val intent = Intent(this@MainActivity,
                        CityDetailsActivity::class.java)
                    startActivity(intent)
                }

                override fun onCityLongClick(view: View, position: Int) {
                    viewModel.delete(Singleton.cities[position])
                }
            })

        binding.mainRecyclerView.layoutManager =
            LinearLayoutManager(this)

        binding.addButton.setOnClickListener {
            Singleton.citySelected = null
            val intent = Intent(this,
                CityDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}
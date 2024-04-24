package com.example.myrecyclerviewapplication.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecyclerviewapplication.R
import com.example.myrecyclerviewapplication.databinding.ActivityMainBinding
import com.example.myrecyclerviewapplication.model.CityRepository
import com.example.myrecyclerviewapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    @Inject lateinit var cityRepository: CityRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        viewModel.citiesLiveData.observe(this){
            binding.mainRecyclerView.adapter?.notifyDataSetChanged()
        }
        binding.mainRecyclerView.adapter =
            CityAdapter(cityRepository, object : CityAdapter.OnCityClickListener {
                override fun onCityClick(view: View, position: Int) {
                    val intent = Intent(this@MainActivity,
                        CityDetailsActivity::class.java)
                    intent.putExtra("cityPosition",position)
                    startActivity(intent)
                }

                override fun onCityLongClick(view: View, position: Int) {
                    viewModel.delete(cityRepository.cities[position])
                }
            })

        binding.mainRecyclerView.layoutManager =
            LinearLayoutManager(this)

        binding.addButton.setOnClickListener {
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
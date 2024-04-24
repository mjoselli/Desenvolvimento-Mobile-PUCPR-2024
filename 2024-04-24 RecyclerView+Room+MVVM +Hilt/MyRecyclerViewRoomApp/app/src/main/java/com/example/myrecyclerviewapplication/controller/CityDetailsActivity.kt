package com.example.myrecyclerviewapplication.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myrecyclerviewapplication.R
import com.example.myrecyclerviewapplication.databinding.ActivityCityDetailsBinding
import com.example.myrecyclerviewapplication.model.City
import com.example.myrecyclerviewapplication.model.CityRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CityDetailsActivity : AppCompatActivity() {
    @Inject lateinit var cityRepository: CityRepository
    var city: City? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityCityDetailsBinding>(this,
                R.layout.activity_city_details
            )
        val cityPosition = intent.getIntExtra("cityPosition",-1)
        if(cityPosition >= 0) {
            cityRepository.cities[cityPosition].apply {
                binding.nameEditText.setText(name)
                binding.populationEditText.setText(population.toString())
                binding.capitalCheckBox.isChecked = isCapital
                city = this
            }
        }

        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val population = binding.populationEditText.text
                .toString().toInt()
            val isCapital = binding.capitalCheckBox.isChecked
            if (city == null) {
                cityRepository.add(City(0, name, population, isCapital))
            }else{
                city?.apply{
                    this.name = name
                    this.population = population
                    this.isCapital = isCapital
                    cityRepository.update(this)
                }
            }
            finish()
        }
    }
}
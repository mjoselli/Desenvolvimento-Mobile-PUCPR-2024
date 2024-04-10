package com.example.myrecyclerviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myrecyclerviewapplication.databinding.ActivityCityDetailsBinding

class CityDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityCityDetailsBinding>(this,
                R.layout.activity_city_details)

        if(Singleton.citySelected >= 0){
            Singleton.cities[Singleton.citySelected].apply {
                binding.nameEditText.setText(name)
                binding.populationEditText.setText(population.toString())
                binding.capitalCheckBox.isChecked = isCapital
            }
        }

        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val population = binding.populationEditText.text
                .toString().toInt()
            val isCapital = binding.capitalCheckBox.isChecked
            if (Singleton.citySelected < 0) {
                Singleton.cities.add(City(name, population, isCapital))
            }else{
                Singleton.cities[Singleton.citySelected].apply{
                    this.name = name
                    this.population = population
                    this.isCapital = isCapital
                }
            }
            finish()
        }
    }
}
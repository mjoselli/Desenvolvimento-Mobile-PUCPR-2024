package com.example.myrecyclerviewapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myrecyclerviewapplication.model.City
import com.example.myrecyclerviewapplication.model.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cityRepository: CityRepository)
    :ViewModel()  {
    val citiesLiveData = MutableLiveData<List<City>>()
    fun add(city: City){
        cityRepository.add(city)
        citiesLiveData.value = cityRepository.cities
    }
    fun delete(city: City){
        cityRepository.delete(city)
        citiesLiveData.value = cityRepository.cities
    }
    fun update(city: City){
        cityRepository.update(city)
        citiesLiveData.value = cityRepository.cities
    }
    fun refresh(){
        citiesLiveData.value = cityRepository.cities
    }
}
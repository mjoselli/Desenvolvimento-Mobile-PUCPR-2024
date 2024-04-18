package com.example.myrecyclerviewapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myrecyclerviewapplication.model.City
import com.example.myrecyclerviewapplication.model.Singleton

class MainViewModel :ViewModel(){
    val citiesLiveData = MutableLiveData<List<City>>()
    fun add(city: City){
        Singleton.add(city)
        citiesLiveData.value = Singleton.cities
    }
    fun delete(city: City){
        Singleton.delete(city)
        citiesLiveData.value = Singleton.cities
    }
    fun update(city: City){
        Singleton.update(city)
        citiesLiveData.value = Singleton.cities
    }
    fun refresh(){
        citiesLiveData.value = Singleton.cities
    }
}
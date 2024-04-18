package com.example.myrecyclerviewapplication.model

import android.content.Context

object Singleton {
    lateinit var cities: List<City>
    private lateinit var dao: CityDao
    var citySelected: City? = null
    fun setContext(context: Context){
        CityDatabase.getInstance(context)?.apply {
            dao = cityDao()
            cities = dao.getAll()
        }
    }
    fun add(city: City){
        dao.insert(city)
        cities = dao.getAll()
    }
    fun update(city: City){
        dao.update(city)
        cities = dao.getAll()
    }
    fun delete(city: City){
        dao.delete(city)
        cities = dao.getAll()
    }
}
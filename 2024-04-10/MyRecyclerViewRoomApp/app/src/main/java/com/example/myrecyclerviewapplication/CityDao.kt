package com.example.myrecyclerviewapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CityDao {
    //C - create
    @Insert
    fun insert(city: City)
    //R - retrieve
    @Query("Select * from cities")
    fun getAll(): List<City>

    @Query("Select * from cities where name = :name")
    fun getByName(name:String): List<City>
    //U - update
    @Update
    fun update(city: City): Int
    //D - delete
    @Delete
    fun delete(city: City): Int
}
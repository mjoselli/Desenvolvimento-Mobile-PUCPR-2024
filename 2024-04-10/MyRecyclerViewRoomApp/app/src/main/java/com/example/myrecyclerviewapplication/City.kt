package com.example.myrecyclerviewapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(index = true)
    var name:String,
    var population:Int,
    @ColumnInfo(name = "capital", defaultValue = "false")
    var isCapital:Boolean = false)
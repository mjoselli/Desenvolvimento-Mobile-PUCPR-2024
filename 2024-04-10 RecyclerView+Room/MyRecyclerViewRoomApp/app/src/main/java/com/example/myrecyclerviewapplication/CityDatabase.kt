package com.example.myrecyclerviewapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [City::class], version = 1)
abstract class CityDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
    companion object{
        private var instance: CityDatabase? = null
        fun getInstance(context: Context): CityDatabase?{
            if (instance == null){
                synchronized(CityDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CityDatabase::class.java,
                        "cities.sqlite"
                            )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}
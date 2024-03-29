package com.avin.news.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avin.news.model.DataConverter
import com.avin.news.model.NewsData

@TypeConverters(DataConverter::class)
@Database(entities = [NewsData::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}
package com.example.nycschoolz.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SchoolEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RoomTypeConverter::class)
abstract class SchoolDatabase:RoomDatabase() {
    abstract fun schoolDAO(): SchoolDAO
}
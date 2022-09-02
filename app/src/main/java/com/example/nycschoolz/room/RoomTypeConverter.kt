package com.example.nycschoolz.room

import androidx.room.TypeConverter
import com.example.nycschoolz.models.SchoolModelItemModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun schoolToString(schoolModel: ArrayList<SchoolModelItemModel>):String = gson.toJson(schoolModel)

    @TypeConverter
    fun stringToSchool(data: String): ArrayList<SchoolModelItemModel> {
        val listType = object : TypeToken<ArrayList<SchoolModelItemModel>>() {}.type
        return gson.fromJson(data, listType)
    }

}
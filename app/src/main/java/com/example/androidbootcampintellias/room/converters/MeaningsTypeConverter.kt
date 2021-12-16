package com.example.androidbootcampintellias.room.converters

import androidx.room.TypeConverter
import com.example.androidbootcampintellias.data.Meaning
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MeaningsTypeConverter {
    @TypeConverter
    fun listToString(meanings: List<Meaning>): String {
        val gson = Gson()
        return gson.toJson(meanings)
    }

    @TypeConverter
    fun stringToList(data: String): List<Meaning> {
        val gson = Gson()
        val itemType = object : TypeToken<List<Meaning>>() {}.type
        return gson.fromJson(data, itemType)
    }
}
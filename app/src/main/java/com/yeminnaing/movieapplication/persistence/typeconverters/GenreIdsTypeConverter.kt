package com.yeminnaing.movieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {
    @TypeConverter
    fun toString(list: List<Int>?): String { //Change to primitive Data Type
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(commentListJsonString: String): List<Int>?{
        val list = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(commentListJsonString, list)
    }
}
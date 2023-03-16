package com.yeminnaing.movieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yeminnaing.movieapplication.data.vos.MovieVO

class MovieListTypeConverter {
    @TypeConverter
    fun toString(list: List<MovieVO>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun movieList(jsonString: String): List<MovieVO>? {
        val movieListVoType = object : TypeToken<List<MovieVO>?>() {}.type
        return Gson().fromJson(jsonString, movieListVoType)
    }
}
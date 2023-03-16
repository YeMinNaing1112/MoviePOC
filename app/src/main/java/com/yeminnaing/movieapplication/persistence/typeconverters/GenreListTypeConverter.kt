package com.yeminnaing.movieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yeminnaing.movieapplication.data.vos.GenreVo

class GenreListTypeConverter {
    @TypeConverter
    fun toString(list: List<GenreVo>?):String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toGenreList(jsonString: String):List<GenreVo>?{
        val genreVoType=object :TypeToken<List<GenreVo>?>(){}.type
        return Gson().fromJson(jsonString,genreVoType)
    }
}
package com.yeminnaing.movieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yeminnaing.movieapplication.data.vos.SpokenLanguageVo

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(list: List<SpokenLanguageVo>?):String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toSpokenLanguageList(jsonString: String):List<SpokenLanguageVo>?{
        val spokenLanguageVoType=object :TypeToken<List<SpokenLanguageVo>?>(){}.type
        return Gson().fromJson(jsonString,spokenLanguageVoType)
    }
}
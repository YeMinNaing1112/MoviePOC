package com.yeminnaing.movieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yeminnaing.movieapplication.data.vos.ProductionCompanyVo

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(list: List<ProductionCompanyVo>?):String{
        return Gson().toJson(list)
    }
   @TypeConverter
    fun toProductionCompanyList(jsonString:String):List<ProductionCompanyVo>?{
        val productionCompanyVoType= object :TypeToken<List<ProductionCompanyVo>?>(){}.type
        return Gson().fromJson(jsonString,productionCompanyVoType)
    }
}
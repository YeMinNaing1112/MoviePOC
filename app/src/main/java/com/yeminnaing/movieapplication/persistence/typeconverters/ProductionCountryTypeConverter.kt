package com.yeminnaing.movieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yeminnaing.movieapplication.data.vos.ProductionCountryVo

class ProductionCountryTypeConverter {
    @TypeConverter
    fun toString(list: List<ProductionCountryVo>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toProductionCountryList(jsonString: String): List<ProductionCountryVo>? {
        val productionCountryVoType = object : TypeToken<List<ProductionCountryVo>?>() {}.type
        return Gson().fromJson(jsonString, productionCountryVoType)
    }
}
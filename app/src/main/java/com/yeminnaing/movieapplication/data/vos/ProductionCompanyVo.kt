package com.yeminnaing.movieapplication.data.vos
import com.google.gson.annotations.SerializedName

import java.util.jar.Attributes.Name

data class ProductionCompanyVo(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("logo_path")
    val logoPath:String?,
    @SerializedName("name")
    val name:Name?,
    @SerializedName("origin_country")
    val originCountry:String?,
)

package com.yeminnaing.movieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.yeminnaing.movieapplication.persistence.typeconverters.MovieListTypeConverter

@Entity(tableName = "actors")
@TypeConverters(
    MovieListTypeConverter::class
)
data class ActorVo(
    @SerializedName("adult")
    @ColumnInfo("adult")
    val adult: Boolean?,

    @SerializedName("gender")
    @ColumnInfo("gender")
    val gender: Int?,

    @SerializedName("id")
    @ColumnInfo("id")
    @PrimaryKey
    val id: Int,

    @SerializedName("known_for")
    @ColumnInfo("known_for")
    val knownFor: List<MovieVO>?,

    @SerializedName("known_for_department")
    @ColumnInfo("known_for_department")
    val knownForDepartment: String?,

    @SerializedName("name")
    @ColumnInfo("name")
    val name: String?,

    @SerializedName("popularity")
    @ColumnInfo("popularity")
    val popularity: Double?,

    @SerializedName("profile_path")
    @ColumnInfo("profile_path")
    val profilePath: String?,

    @SerializedName("original_name")
    @ColumnInfo("original_name")
    val originalName: String?,

    @SerializedName("cast_id")
    @ColumnInfo("cast_id")
    val castId: String?,

    @SerializedName("character")
    @ColumnInfo("character")
    val character: String?,

    @SerializedName("creditId")
    @ColumnInfo("creditId")
    val creditId: String?,

    @SerializedName("order")
    @ColumnInfo("order")
    val order: Int?,
)

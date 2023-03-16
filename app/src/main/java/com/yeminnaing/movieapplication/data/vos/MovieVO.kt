package com.yeminnaing.movieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.yeminnaing.movieapplication.persistence.typeconverters.*

@Entity(tableName = "movies")
@TypeConverters(
    GenreIdsTypeConverter::class,
    GenreListTypeConverter::class,
    ProductionCompanyTypeConverter::class,
    ProductionCountryTypeConverter::class,
    SpokenLanguageTypeConverter::class,
)
data class MovieVO(
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backDropPath: String?,

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIDs: List<Int>?,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Long,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overView: String?,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    val video: Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,

    @SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    val homePage: String?,

    @SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    val imbdId: String?,

    @SerializedName("production_companies")
    @ColumnInfo(name = "production_companies")
    val productionCompanies: List<ProductionCompanyVo>?,

    @SerializedName("production_countries")
    @ColumnInfo(name = "production_countries")
    val productionCountry: List<ProductionCountryVo>?,

    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    val revenue: Float?,

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runTime: Int?,

    @SerializedName("spoken_languages")
    @ColumnInfo(name = "spoken_languages")
    val spokenLanguage: List<SpokenLanguageVo>?,

    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String?,

    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    val tagLine: String?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<GenreVo>?,

    @ColumnInfo("type")
    var type:String?,

    @ColumnInfo("genreType")
    var genreType:String?

    ) {
    fun getRatingBaseOnFiveStar(): Float {
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }

    fun getGenresAsCommaSeparatedString(): String {
        return genres?.map { it.name }?.joinToString(",") ?: ""
    }

    fun getProductionCountriesAsCommaSeparated(): String {
        return productionCountry?.map { it.name }?.joinToString(",") ?: ""
    }

}
const val NOW_PLAYING="NOW_PLAYING"
const val POPULAR="POPULAR"
const val TOP_RATED="TOP_RATED"


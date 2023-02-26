package com.yeminnaing.movieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class MovieVO(
    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backDropPath: String?,

    @SerializedName("genre_ids")
    val genreIDs: List<Int>?,

    @SerializedName("id")
    val id: Long,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overView: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("homepage")
    val homePage: String?,

    @SerializedName("imdb_id")
    val imbdId: String?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyVo>?,

    @SerializedName("production_countries")
    val productionCountry: List<ProductionCountryVo>?,

    @SerializedName("revenue")
    val revenue: Float?,

    @SerializedName("runtime")
    val runTime: Int?,

    @SerializedName("spoken_languages")
    val spokenLanguage: List<SpokenLanguageVo>?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tagline")
    val tagLine: String?,

    @SerializedName("genres")
    val genres: List<GenreVo>?,


    ) {
    fun getRatingBaseOnFiveStar(): Float {
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }

    fun getGenresAsCommaSeparatedString(): String {
        return genres?.map { it.name }?.joinToString { "," } ?: ""
    }

    fun getProductionCountriesAsCommaSeparated(): String {
        return productionCountry?.map { it.name }?.joinToString(",") ?: ""
    }

}

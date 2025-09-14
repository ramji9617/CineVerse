package com.project.movieapp.data.remote.movieDetail

import com.google.gson.annotations.SerializedName

data class MovieDetailDto (
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("poster_path") val posterUrl: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("adult") val isAdult : Boolean,
    @SerializedName("budget") val budget: Long,
    val genres: List<Genre>,
    val runtime: Int


    )

data class Genre(
    val id: Int,
    val name: String
)
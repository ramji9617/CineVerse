package com.project.movieapp.data.remote.movieList

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<MovieSummaryDto>
)


data class MovieSummaryDto(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")  val posterUrl: String?,
    @SerializedName("adult")  val isAdult: Boolean,
    @SerializedName("release_date")  val releaseDate: String
)
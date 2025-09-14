package com.project.movieapp.domain.entity


data class MovieDetailEntity(
    val id: Int,
    val title: String = "N/A",
    val overview: String = "N/A",
    val voteAverage: Double = 0.0,
    val posterUrl: String? = null,
    val originalLanguage: String = "En",
    val isAdult: Boolean = false,
    val genres :String = "",
    val budget: String = "",
   val runtime: String = ""
)
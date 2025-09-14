package com.project.movieapp.domain.entity

data class MovieSummaryEntity(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val isAdult: Boolean,
    val releaseDate: String,
)

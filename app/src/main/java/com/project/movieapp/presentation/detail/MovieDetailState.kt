package com.project.movieapp.presentation.detail

import com.project.movieapp.domain.entity.MovieDetailEntity

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetailEntity? = null,
    val error: String = ""
)

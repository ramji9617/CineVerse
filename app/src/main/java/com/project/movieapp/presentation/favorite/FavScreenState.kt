package com.project.movieapp.presentation.favorite

import com.project.movieapp.domain.entity.MovieSummaryEntity

data class FavScreenState (

    val isLoading: Boolean = false,
    val movies: List<MovieSummaryEntity> = emptyList(),
    val error: String = ""

)
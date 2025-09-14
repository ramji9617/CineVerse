package com.project.movieapp.domain.usecases

import com.project.movieapp.domain.entity.MovieSummaryEntity
import com.project.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class FavMovieOperationsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend fun addMovieToFavorites(movie: MovieSummaryEntity) {
        repository.addToFavMovie(movie)

    }

    suspend fun deleteMovieFromFavorites(id: Int) {
        repository.removeMovieFromFav(id)

    }

    suspend fun isMovieInFavorites(movieId: Int): Boolean {
        return repository.isAlreadyInFav(movieId)
    }



}
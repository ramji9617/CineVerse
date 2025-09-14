package com.project.movieapp.domain.usecases

import com.project.movieapp.core.Resources
import com.project.movieapp.domain.entity.MovieSummaryEntity
import com.project.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetFavMoviesUseCase @Inject constructor(
    private val repo : MovieRepository
){

    operator fun invoke(): Flow<Resources<List<MovieSummaryEntity>>> =
        repo.getFavMovies()
            .map<List<MovieSummaryEntity>, Resources<List<MovieSummaryEntity>>> { list ->
                Resources.Success(list)
            }
            .onStart{ emit(Resources.Loading()) }
            .catch { e ->
                emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
}
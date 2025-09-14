package com.project.movieapp.domain.usecases

import androidx.paging.PagingData
import com.project.movieapp.domain.entity.MovieSummaryEntity
import com.project.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
     operator fun invoke(): Flow<PagingData<MovieSummaryEntity>> = repository.getUpcomingMovies()

}
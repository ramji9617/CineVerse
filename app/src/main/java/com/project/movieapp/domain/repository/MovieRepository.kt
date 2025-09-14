package com.project.movieapp.domain.repository

import androidx.paging.PagingData
import com.project.movieapp.domain.entity.MovieDetailEntity
import com.project.movieapp.domain.entity.MovieSummaryEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

     fun getUpcomingMovies(): Flow<PagingData<MovieSummaryEntity>>
    suspend fun getMovieDetail(id: Int): MovieDetailEntity?

    fun getFavMovies(): Flow<List<MovieSummaryEntity>>

  suspend  fun removeMovieFromFav(id: Int) : Int

    suspend fun addToFavMovie(movie: MovieSummaryEntity)

    suspend fun isAlreadyInFav(movieId: Int): Boolean

}
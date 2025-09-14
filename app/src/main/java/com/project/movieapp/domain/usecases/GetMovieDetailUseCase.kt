package com.project.movieapp.domain.usecases

import android.util.Log
import coil.network.HttpException
import com.project.movieapp.core.Resources
import com.project.movieapp.domain.entity.MovieDetailEntity
import com.project.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository

) {
     operator fun invoke(id: Int): Flow<Resources<MovieDetailEntity>> = flow {

         try {
             emit(Resources.Loading<MovieDetailEntity>())
             val movie = repository.getMovieDetail(id)
             val successResource = Resources.Success(movie)
             emit(successResource)
         }catch (e : HttpException){
             emit(Resources.Error<MovieDetailEntity>(e.localizedMessage ?: "An unexpected error occurred"))
         } catch(_: IOException) {
             emit(Resources.Error<MovieDetailEntity>("Couldn't reach server. Check your internet connection."))
         }

     }


}
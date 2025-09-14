package com.project.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.movieapp.BuildConfig
import com.project.movieapp.data.local.FavMoviesDao
import com.project.movieapp.data.mapper.toEntity
import com.project.movieapp.data.mapper.toFavEntity
import com.project.movieapp.data.paging.MoviePagingSource
import com.project.movieapp.data.remote.movieDetail.MovieDetailApi
import com.project.movieapp.data.remote.movieList.MovieListApi
import com.project.movieapp.domain.entity.MovieDetailEntity
import com.project.movieapp.domain.entity.MovieSummaryEntity
import com.project.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val summaryApi: MovieListApi,
    private val detailApi: MovieDetailApi,
    private val dao: FavMoviesDao
) : MovieRepository {

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    override fun getUpcomingMovies(): Flow<PagingData<MovieSummaryEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(summaryApi, API_KEY) }
        ).flow
    }

    override suspend fun getMovieDetail(id: Int): MovieDetailEntity? {
        val dto = detailApi.getMovieDetail(
            id,
            API_KEY
        )

        return dto.toEntity()

    }

    override fun getFavMovies(): Flow<List<MovieSummaryEntity>> {

        return  dao.getAllFavMovies().map { list ->
                list.map { it.toEntity() }
            }
        }


    override suspend fun removeMovieFromFav(id: Int): Int {

      return withContext(Dispatchers.IO) { dao.deleteFavMovie(id)}

    }

    override suspend fun addToFavMovie(movie: MovieSummaryEntity) {

        return withContext(Dispatchers.IO) { dao.addToFavMovie(movie.toFavEntity())}
    }

    override suspend fun isAlreadyInFav(movieId: Int): Boolean {
        return withContext(Dispatchers.IO) { dao.isMovieInFavorites(movieId) }
    }


}


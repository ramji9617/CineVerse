package com.project.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavMoviesDao {

    @Query("SELECT * FROM fav_movies")
     fun getAllFavMovies(): Flow<List<FavMoviesEntity>>

    @Query("DELETE FROM fav_movies WHERE id = :id")
    suspend fun deleteFavMovie(id:Int) :Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavMovie(movie: FavMoviesEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM fav_movies WHERE id = :movieId)")
    suspend fun isMovieInFavorites(movieId: Int): Boolean

}
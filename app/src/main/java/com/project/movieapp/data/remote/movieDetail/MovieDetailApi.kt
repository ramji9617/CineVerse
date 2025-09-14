package com.project.movieapp.data.remote.movieDetail

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailApi {
    @GET("movie/{id}")
   suspend fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): MovieDetailDto
}
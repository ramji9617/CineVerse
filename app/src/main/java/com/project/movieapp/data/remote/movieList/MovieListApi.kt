package com.project.movieapp.data.remote.movieList

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListApi {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "en-IN",
        @Query("region") region: String = "IN",
        @Query("api_key") apiKey: String
    ) : MovieResponseDto
}
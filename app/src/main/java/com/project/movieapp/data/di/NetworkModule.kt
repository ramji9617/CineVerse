package com.project.movieapp.data.di

import com.project.movieapp.core.BASE_URL
import com.project.movieapp.data.remote.movieDetail.MovieDetailApi
import com.project.movieapp.data.remote.movieList.MovieListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieSummaryApi(retrofit: Retrofit): MovieListApi =
        retrofit.create(MovieListApi::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailApi(retrofit: Retrofit): MovieDetailApi =
        retrofit.create(MovieDetailApi::class.java)
}

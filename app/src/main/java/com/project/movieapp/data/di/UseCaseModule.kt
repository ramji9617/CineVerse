package com.project.movieapp.data.di

import com.project.movieapp.domain.repository.MovieRepository
import com.project.movieapp.domain.usecases.FavMovieOperationsUseCase
import com.project.movieapp.domain.usecases.GetFavMoviesUseCase
import com.project.movieapp.domain.usecases.GetMovieDetailUseCase
import com.project.movieapp.domain.usecases.GetUpcomingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetUpcomingUseCase(repository: MovieRepository) =
        GetUpcomingMoviesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetDetailUseCase(repository: MovieRepository) =
        GetMovieDetailUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavMoviesUseCase(repository: MovieRepository) = GetFavMoviesUseCase(repository)

    @Provides
    @Singleton
    fun provideFavMovieOperationsUseCase(repository: MovieRepository) =
        FavMovieOperationsUseCase(repository)

}
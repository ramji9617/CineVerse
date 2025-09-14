package com.project.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.movieapp.domain.entity.MovieSummaryEntity
import com.project.movieapp.domain.usecases.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    val movies: Flow<PagingData<MovieSummaryEntity>> = getUpcomingMoviesUseCase()
        .cachedIn(viewModelScope)



}
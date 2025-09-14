package com.project.movieapp.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.movieapp.core.Resources
import com.project.movieapp.domain.usecases.FavMovieOperationsUseCase
import com.project.movieapp.domain.usecases.GetFavMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    getFavMoviesUseCase: GetFavMoviesUseCase,
    private val favMovieOperationsUseCase: FavMovieOperationsUseCase

) : ViewModel() {

    val state: StateFlow<FavScreenState> = getFavMoviesUseCase()
        .map { result ->
            when (result) {
                is Resources.Loading -> FavScreenState(isLoading = true)
                is Resources.Success -> FavScreenState(

                    movies = result.data ?: emptyList())
                is Resources.Error -> FavScreenState(error = result.message ?: "Unexpected error")
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavScreenState(isLoading = true)   // initial UI state
        )

     fun removeFavMovie(id :Int){
      viewModelScope.launch {
          favMovieOperationsUseCase.deleteMovieFromFavorites(id)
      }
    }

}

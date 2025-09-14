package com.project.movieapp.presentation.detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.movieapp.core.Resources
import com.project.movieapp.domain.entity.MovieSummaryEntity
import com.project.movieapp.domain.usecases.FavMovieOperationsUseCase
import com.project.movieapp.domain.usecases.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val getFavMovieOperationsUseCase: FavMovieOperationsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    fun loadMovie(movieId: Int) {


        movieDetailUseCase(movieId).onEach { result ->
            Log.d("MovieDetailVM", "Result emitted: $result")
            when (result) {
                is Resources.Success -> {

                    _state.value = MovieDetailState(movie = result.data)

                }

                is Resources.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }

                is Resources.Error -> {
                    _state.value =
                        MovieDetailState(error = result.message ?: "An unexpected error occurred")
                }


            }

        }.launchIn(viewModelScope)
    }

    fun formatDate(input: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())

        val date = inputFormat.parse(input)

        return outputFormat.format(date ?: "")
    }

    fun mapMovieDate(rawDate: String): String {
        return formatDate(rawDate)
    }

    fun addToFav(movie: MovieSummaryEntity) {
        viewModelScope.launch {
            getFavMovieOperationsUseCase.addMovieToFavorites(movie)
        }
    }

    suspend fun isAlreadyInFav(movieId: Int): Boolean {


        val isFav = getFavMovieOperationsUseCase.isMovieInFavorites(movieId)

        return isFav

    }

}
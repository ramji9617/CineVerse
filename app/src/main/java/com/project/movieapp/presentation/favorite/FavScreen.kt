package com.project.movieapp.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.movieapp.presentation.home.ui_helper.GridItemStructure
import com.project.movieapp.presentation.theme.PurpleViolet
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun FavScreen(
    modifier: Modifier = Modifier,
    viewModel: FavViewModel = hiltViewModel(),

    ) {
    // observe StateFlow from ViewModel
    val state by viewModel.state.collectAsState()
    var isRemoveDialogShowing: Boolean by remember { mutableStateOf(false) }
    var movieId: Int? by remember { mutableStateOf(null) }
    val currentDate = LocalDate.now()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(PurpleViolet),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Favorites",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }

        // UI State
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Loading...")
                }
            }

            state.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(state.error, color = Color.Red)
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(120.dp),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.movies) { movie ->
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Match your string format
                        val parsedDate = LocalDate.parse(movie.releaseDate, formatter)
                        if (parsedDate.isBefore(currentDate) ){
                            viewModel.removeFavMovie(movie.id)
                        }
                        GridItemStructure(
                            posterUrl = movie.posterUrl,
                            releaseDate = movie.releaseDate,
                            title = movie.title,
                            isAdult = movie.isAdult,
                            onClick = {
                                movieId = movie.id
                                isRemoveDialogShowing = true

                            }
                        )

                    }
                }
            }
        }


    }

    if (isRemoveDialogShowing && movieId != null){
        ShowDialog(
            onDismissDialog = { isRemoveDialogShowing = false },
            onConfirmDialog = {
                viewModel.removeFavMovie(movieId!!)
                isRemoveDialogShowing = false
            }
        )
    }

}



@Composable
fun ShowDialog(
    onDismissDialog: () -> Unit,
    onConfirmDialog: () -> Unit,
) {

    AlertDialog(
        icon = {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Fav Section"
            )
        },
        title = { Text("Remove From Favorite") },
        text = { Text("Are you sure you want to remove this movie from favorites?") },
        confirmButton = {
            TextButton(
                onClick = onConfirmDialog
            ) {
                Text("Ok")
            }
        },
        onDismissRequest = onDismissDialog,

        )

}
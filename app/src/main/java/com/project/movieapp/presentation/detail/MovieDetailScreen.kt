package com.project.movieapp.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.movieapp.R
import com.project.movieapp.domain.entity.MovieSummaryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MovieDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    movieReleaseDate: String,
    viewModel: MovieDetailViewModel,
) {
    val context = LocalContext.current
    // Observe the mutableStateOf as Compose State
    val state by viewModel.state

val paddingBtwItems = 6.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())

        ) {

        state.movie?.let { movie ->
Surface(
    shape = RoundedCornerShape(12.dp),
    tonalElevation = 8.dp,
    shadowElevation = 12.dp,
    modifier = Modifier.wrapContentSize()
        .padding(paddingBtwItems)

) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.posterUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Poster Image",
        error= painterResource(R.drawable.error_placeholder),
        contentScale = ContentScale.Crop,
        modifier = Modifier.padding(vertical = paddingBtwItems, horizontal = 0.dp)

    )
}
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = paddingBtwItems , horizontal = 12.dp)
            )

            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = paddingBtwItems , horizontal = 12.dp )
            ) {
                Text(
                    text = viewModel.mapMovieDate(movieReleaseDate),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,


                    )

                Text(
                    text = movie.originalLanguage,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
              //  textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp , vertical = 15.dp)
            )
            HorizontalDivider(thickness = 1.dp)

            Text(
                text = "Genres : ${movie.genres}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp , vertical = paddingBtwItems)
            )

            Text(
                text = "Budget : ${movie.budget}",
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp , vertical = paddingBtwItems)
            )
            Text(
                text = "PornoGraphic : ${movie.isAdult}",
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp , vertical = paddingBtwItems)
            )

            Text(
                text = "Rating : ${if (movie.voteAverage == 0.0) "Not disclosed yet"
                            else movie.voteAverage.toString()+"/10"}",
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp , vertical = paddingBtwItems)
            )
            Text(
                text = "Runtime : ${movie.runtime}",
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp , vertical = paddingBtwItems)
            )
            Text(movie.id.toString() ,modifier = Modifier.padding(horizontal = 12.dp , vertical = paddingBtwItems))

            ElevatedButton(
                onClick = {

                    CoroutineScope(Dispatchers.Main).launch {
                        val isPresented = viewModel.isAlreadyInFav(movie.id)

                        if (isPresented) {
                            Toast.makeText(context, "Already in favorites", Toast.LENGTH_SHORT)
                                .show()
                        } else {

                            viewModel.addToFav(
                                MovieSummaryEntity(
                                    id = movie.id,
                                    title = movie.title,
                                    posterUrl = movie.posterUrl ?: "",
                                    releaseDate = movieReleaseDate,
                                    isAdult = movie.isAdult
                                )
                            )
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth().height(65.dp).
                padding(horizontal = 12.dp , vertical = paddingBtwItems)

            ) {
                Text("Add to favorites" ,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Start,
                   )

            }
        }

        // Show error if any
        if (state.error.isNotBlank()) {
            Text(text = state.error, color = Color.Red)
        }

        // Show loading indicator
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

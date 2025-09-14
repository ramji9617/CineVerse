package com.project.movieapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.movieapp.presentation.home.ui_helper.GridItemStructure
import com.project.movieapp.presentation.theme.PurpleViolet


@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onDetailClick: (id:Int , releaseDate :String) -> Unit
) {

    val movies = viewModel.movies.collectAsLazyPagingItems()



    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(PurpleViolet),
            contentAlignment = Alignment.Center // centers content both vertically & horizontally
        ) {
            Text(
                text = "MovieApp",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(movies.itemCount) { index ->
            val movie = movies[index]

                GridItemStructure(
                    movie?.posterUrl,
                    movie?.releaseDate,
                    movie?.title ?: "N/A",
                  movie?.isAdult ?: false,
                    onClick = { onDetailClick(movie!!.id, movie.releaseDate) }
                )


        }

            movies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> item { Text("Loading...") }
                    loadState.append is LoadState.Loading -> item { Text("Loading more...") }
                    loadState.refresh is LoadState.Error -> item { Text("Error loading data", color = Color.Red) }
                }
            }

        }





    }


    }


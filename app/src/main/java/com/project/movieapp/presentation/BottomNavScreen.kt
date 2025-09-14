package com.project.movieapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

import com.project.movieapp.presentation.home.HomeScreen
import com.project.movieapp.presentation.utils.BottomBar
import com.project.movieapp.core.HomeNav
import com.project.movieapp.core.MultiStackNavigator
import com.project.movieapp.core.NavKey
import com.project.movieapp.core.FavNav
import com.project.movieapp.presentation.detail.MovieDetailViewModel
import com.project.movieapp.presentation.detail.MovieDetailScreen
import com.project.movieapp.presentation.favorite.FavScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@Composable
fun BottomNavScreen(
    vm: NavigatorViewModel = hiltViewModel()
) {
    // 2 tabs, 2 stacks, start on Home
    val navigator = vm.navigator
    val tabs: List<NavKey> = listOf(HomeNav.Root, FavNav.Root)

    Scaffold(
        bottomBar = { BottomBar(tabs = tabs, navigator = navigator) }
    ) { innerPadding ->
        // Render current destination(s) from merged backstack
        NavDisplay(
            backStack = navigator.backStack,
            onBack = { navigator.pop() },
            entryProvider = entryProvider {
                // --- Home stack ---
                entry<HomeNav.Root> {
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        onDetailClick = {id , releaseDate -> navigator.push(HomeNav.Detail(id, releaseDate)) }
                    )
                }
                entry<HomeNav.Detail> {

                    val viewModel: MovieDetailViewModel = hiltViewModel()

                    LaunchedEffect(it.id) {
                        viewModel.loadMovie(it.id)
                    }

                    MovieDetailScreen(
                        viewModel = viewModel,
                        movieReleaseDate = it.releaseDate,
                        onBackClick = { navigator.pop() },
                    modifier = Modifier.padding(innerPadding)
                    )
                }

                // --- Notes stack ---
                entry<FavNav.Root> {
                    FavScreen(
//                        onNoteClick = { id -> navigator.push(FavNav.Detail(id)) },
                        modifier = Modifier.padding(innerPadding),

                    )
                }
                entry<FavNav.Detail> { args ->
//                    NoteDetailScreen(
//                        noteId = args.id,
//                        onBackClick = { navigator.pop() },
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        )
    }
}

@HiltViewModel
class NavigatorViewModel @Inject constructor() : ViewModel() {
    // single instance of navigator, survives config changes
    val navigator = MultiStackNavigator<NavKey>(HomeNav.Root)
}

package com.project.movieapp.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

// Common
sealed interface NavKey

interface BottomNavItem {
    val icon: ImageVector
    val title: String
}

// Home feature
@Serializable
sealed class HomeNav : NavKey {
    @Serializable
    data object Root : HomeNav(), BottomNavItem {
        override val icon = Icons.Default.Home
        override val title = "Home"
    }
    @Serializable
    data class Detail(val id: Int , val releaseDate : String = "") : HomeNav()
}

// Notes feature
@Serializable
sealed class FavNav : NavKey {
    @Serializable
    data object Root : FavNav(), BottomNavItem {
        override val icon = Icons.Default.Star
        override val title = "Fav"
    }
    @Serializable
    data class Detail(val id: Long) : FavNav()
}

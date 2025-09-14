package com.project.movieapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_movies")
data class FavMoviesEntity (

    @PrimaryKey()
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val isAdult: Boolean,
    val releaseDate: String,
)
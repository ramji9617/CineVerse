package com.project.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [FavMoviesEntity::class],
    version = 1,
    exportSchema = false

)
abstract class FavMoviesDatabase : RoomDatabase() {

    abstract fun favMoviesDao(): FavMoviesDao

}
package com.project.movieapp.data.di


import android.content.Context
import androidx.room.Room
import com.project.movieapp.data.local.FavMoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalDBModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context
    ): FavMoviesDatabase = Room.databaseBuilder(
        app,
        FavMoviesDatabase::class.java,
        "fav_movies_db"
    ).build()

    @Provides
    @Singleton
    fun provideFavMoviesDao(db: FavMoviesDatabase) = db.favMoviesDao()


}




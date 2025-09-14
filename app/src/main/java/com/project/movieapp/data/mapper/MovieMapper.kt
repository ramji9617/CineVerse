package com.project.movieapp.data.mapper


import com.project.movieapp.data.local.FavMoviesEntity
import com.project.movieapp.data.remote.movieDetail.MovieDetailDto
import com.project.movieapp.data.remote.movieList.MovieSummaryDto
import com.project.movieapp.domain.entity.MovieDetailEntity
import com.project.movieapp.domain.entity.MovieSummaryEntity
import java.util.Locale


private const val IMAGE_BASE_SMALL = "https://image.tmdb.org/t/p/w500"
private const val IMAGE_BASE_LARGE = "https://image.tmdb.org/t/p/w780"

fun MovieSummaryDto.toEntity() = MovieSummaryEntity(
    id = id,
    title = title,
    posterUrl = posterUrl.let { IMAGE_BASE_SMALL + it },
    isAdult = isAdult,
    releaseDate = releaseDate
)

fun MovieDetailDto.toEntity() = MovieDetailEntity(
    id = id,
    title = title,
    overview = overview,
    voteAverage = voteAverage,
    posterUrl = posterUrl.let { IMAGE_BASE_LARGE + it },
    originalLanguage = originalLanguage,
    isAdult = isAdult,
    genres = genres.joinToString(",") { it.name },
    budget = formatDollarAmount(budget),
    runtime = convertMinutes(runtime)
)

fun FavMoviesEntity.toEntity() = MovieSummaryEntity(
    id = id,
    title = title,
    posterUrl = posterUrl,
    isAdult = isAdult,
    releaseDate = releaseDate
)
fun MovieSummaryEntity.toFavEntity() = FavMoviesEntity(
    id = id,
    title = title,
    posterUrl = posterUrl,
    isAdult = isAdult,
    releaseDate = releaseDate
)

fun convertMinutes(totalMinutes: Int): String {

    if (totalMinutes == 0) {
        return "Not disclosed yet"
    }
    if (totalMinutes < 60) {
        return "${totalMinutes}m"
    }

    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return "${hours}h ${minutes}m"
}

fun formatDollarAmount(amount: Long): String {
    if (amount == 0L) {
        return "Not disclosed yet"
    }


    return when {
        amount >= 1_000_000_000 -> String.format(Locale.US, "$%.1fB", amount / 1_000_000_000.0)
        amount >= 1_000_000 -> String.format(Locale.US, "$%.1fM", amount / 1_000_000.0)
        amount >= 1_000 -> String.format(Locale.US, "$%.1fK", amount / 1_000.0)
        else -> "$${amount}"
    }


}





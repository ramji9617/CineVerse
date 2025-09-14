package com.project.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.movieapp.data.mapper.toEntity
import com.project.movieapp.data.remote.movieList.MovieListApi
import com.project.movieapp.domain.entity.MovieSummaryEntity

class MoviePagingSource(
    private val api: MovieListApi,
    private val apiKey: String

) : PagingSource<Int , MovieSummaryEntity> (){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSummaryEntity> {
        return try {
            val page = params.key ?: 1
            val response = api.getUpcomingMovies(page, apiKey = apiKey)
            val movies = response.results.map { it.toEntity() }
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.totalPages) page + 1 else null
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieSummaryEntity>): Int? {

        return state.anchorPosition?.let { anchor ->
            val anchorPage = state.closestPageToPosition(anchor)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }


}
package com.example.rxjavakoinpoc.movies.viewstate

import com.example.rxjavakoinpoc.movies.backend.ApiMoviesListResponse
import com.example.rxjavakoinpoc.movies.backend.Results
import com.example.rxjavakoinpoc.rx.Converter

class MoviesListConverter : Converter<ApiMoviesListResponse, MoviesListViewState> {
    override fun apply(apiMoviesListResponse: ApiMoviesListResponse): MoviesListViewState {

        val moviesInfoList = mutableListOf<MoviesInfo>()
        apiMoviesListResponse.results.map { movieResult ->
            val movieInfo = getMoviesInfo(movieResult)
            moviesInfoList.add(movieInfo)
        }
        return MoviesListViewState.Success(moviesInfoList)
    }

    private fun getMoviesInfo(movieResult: Results): MoviesInfo {
        return MoviesInfo(
            title = movieResult.original_title.orEmpty(),
            adultRated = movieResult.adult ?: false,
            language = movieResult.original_language.orEmpty(),
            date = movieResult.release_date.orEmpty(),
            thumbnailImageUrl = movieResult.backdrop_path.orEmpty(),
            imageUrl = movieResult.poster_path.orEmpty(),
            overview = movieResult.overview.orEmpty(),
            popularity = movieResult.popularity ?: 0.0
        )
    }


}
package com.example.rxjavakoinpoc.movies.backend

import io.reactivex.Single

class MoviesApiFetcher(private val moviesBackend: MoviesBackend) : MoviesFetcher {
    override fun getMovies(map: Map<String, String>): Single<ApiMoviesListResponse> {
       return moviesBackend.getMovies(map)
    }
}
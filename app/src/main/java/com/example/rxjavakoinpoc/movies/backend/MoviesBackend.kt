package com.example.rxjavakoinpoc.movies.backend

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MoviesBackend {
    @GET("movie/now_playing")
    fun getMovies(
        @QueryMap map: Map<String, String>
    ): Single<ApiMoviesListResponse>
}


package com.example.rxjavakoinpoc.movies.backend

import io.reactivex.Single
import retrofit2.http.QueryMap

interface MoviesFetcher {
    fun getMovies(@QueryMap map: Map<String, String>): Single<ApiMoviesListResponse>
}
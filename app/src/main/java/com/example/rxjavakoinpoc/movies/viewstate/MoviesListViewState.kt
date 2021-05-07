package com.example.rxjavakoinpoc.movies.viewstate

import com.example.rxjavakoinpoc.api.ErrorType

sealed class MoviesListViewState {

    object Loading : MoviesListViewState()

    data class Success(val moviesInfoList : List<MoviesInfo>) : MoviesListViewState()

    data class Error(val errorType : ErrorType) : MoviesListViewState()

}
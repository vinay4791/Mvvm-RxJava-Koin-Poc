package com.example.rxjavakoinpoc.movies

import androidx.lifecycle.MutableLiveData
import com.example.rxjavakoinpoc.api.ErrorType
import com.example.rxjavakoinpoc.base.BaseViewModel
import com.example.rxjavakoinpoc.db.MovieDb
import com.example.rxjavakoinpoc.movies.viewstate.MoviesInfo
import com.example.rxjavakoinpoc.movies.viewstate.MoviesListViewState
import io.reactivex.disposables.CompositeDisposable

class MoviesListViewModel constructor(
    private val repository: MoviesListRepository,
    private val moviesDb: MovieDb
) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val moviesListData = MutableLiveData<List<MoviesInfo>>()
    private val errorResponseData = MutableLiveData<ErrorType>()
    private val loadingStateData = MutableLiveData<Boolean>()

    fun moviesListData(): MutableLiveData<List<MoviesInfo>> = moviesListData
    fun errorResponseData(): MutableLiveData<ErrorType> = errorResponseData
    fun loadingStateData(): MutableLiveData<Boolean> = loadingStateData

    fun fetchMoviesList(
        api_key: String,
        language: String,
        page: Int
    ) {
        disposable.add(
            repository.fetchMoviesList(api_key, language, page)
                .subscribe({ response ->
                    processMoviesListData(response)
                },
                    { error ->
                        errorResponseData.postValue(ErrorType.UNKNOWN)
                    })
        )

    }

    private fun processMoviesListData(response: MoviesListViewState) {
        when (response) {
            is MoviesListViewState.Loading -> loadingStateData.postValue(true)
            is MoviesListViewState.Success -> {
                moviesListData.postValue(response.moviesInfoList)
                loadingStateData.postValue(false)
            }
            is MoviesListViewState.Error -> {
                loadingStateData.postValue(false)
                errorResponseData.postValue(ErrorType.UNKNOWN)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
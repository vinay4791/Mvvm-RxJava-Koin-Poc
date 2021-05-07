package com.example.rxjavakoinpoc.movies

import androidx.lifecycle.MutableLiveData
import com.example.rxjavakoinpoc.api.ErrorType
import com.example.rxjavakoinpoc.base.BaseViewModel
import com.example.rxjavakoinpoc.movies.viewstate.MoviesListViewState
import io.reactivex.disposables.CompositeDisposable

class MoviesListViewModel constructor(private val repository: MoviesListRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val moviesListData = MutableLiveData<MoviesListViewState>()

    fun moviesListData(): MutableLiveData<MoviesListViewState> = moviesListData

    fun fetchMoviesList(
        api_key: String,
        language: String,
        page: Int
    ) {
        disposable.add(
            repository.fetchMoviesList(api_key, language, page)
                .subscribe({ response ->
                    moviesListData.postValue(response)
                },
                    { error ->
                        moviesListData.postValue(MoviesListViewState.Error(ErrorType.UNKNOWN))
                    })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
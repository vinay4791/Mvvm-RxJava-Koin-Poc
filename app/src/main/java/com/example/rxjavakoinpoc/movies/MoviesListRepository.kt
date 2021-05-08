package com.example.rxjavakoinpoc.movies

import android.util.Log
import com.example.rxjavakoinpoc.api.ErrorType
import com.example.rxjavakoinpoc.db.MovieDb
import com.example.rxjavakoinpoc.movies.backend.MoviesApiFetcher
import com.example.rxjavakoinpoc.movies.viewstate.MoviesListConverter
import com.example.rxjavakoinpoc.movies.viewstate.MoviesListViewState
import com.example.rxjavakoinpoc.rx.SchedulingStrategyFactory
import com.example.rxjavakoinpoc.util.Utils
import com.squareup.moshi.JsonEncodingException
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import retrofit2.HttpException
import java.net.UnknownHostException
import io.reactivex.functions.Function

class MoviesListRepository constructor(
    private val moviesApiFetcher: MoviesApiFetcher,
    private val moviesListConverter: MoviesListConverter,
    private val moviesDb: MovieDb,
    private val utils: Utils,
    private val schedulingStrategyFactory: SchedulingStrategyFactory
) {

    fun fetchMoviesList(api_key: String,
                        language: String,
                        page: Int): Observable<MoviesListViewState> {

        if(utils.hasInternet()){
            val moviesApiFetcherObservable = moviesApiFetcher.
            getMovies(getMovieListFetchQueryMap(api_key, language, page)).toObservable()
            return moviesApiFetcherObservable
                .map(moviesListConverter)
                .startWith(MoviesListViewState.Loading)
                .compose(ErrorConverter())
                .compose(schedulingStrategyFactory.create())
        } else{
            Log.d("vinay", "no internet")
            val moviesApiFetcherObservable = moviesApiFetcher.
            getMovies(getMovieListFetchQueryMap(api_key, language, page)).toObservable()
            return moviesApiFetcherObservable
                .map(moviesListConverter)
                .startWith(MoviesListViewState.Loading)
                .compose(ErrorConverter())
                .compose(schedulingStrategyFactory.create())
        }

    }

    private fun getMovieListFetchQueryMap(
        api_key: String,
        language: String,
        page: Int
    ): Map<String, String> {
        val queryMap = HashMap<String, String>()
        queryMap["api_key"] = api_key
        queryMap["language"] = language
        queryMap["page"] = page.toString()
        return queryMap
    }

    class ErrorConverter : ObservableTransformer<MoviesListViewState, MoviesListViewState> {
        override fun apply(upstream: Observable<MoviesListViewState>): ObservableSource<MoviesListViewState> {
            return upstream.onErrorResumeNext(Function<Throwable, ObservableSource<MoviesListViewState>> { cause ->
                Observable.just(convertToCause(cause))
            })
        }

        private fun convertToCause(cause: Throwable): MoviesListViewState {
            return when (cause) {
                is JsonEncodingException -> MoviesListViewState.Error(ErrorType.UNKNOWN)
                is UnknownHostException -> MoviesListViewState.Error(ErrorType.NO_INTERNET_CONNECTION)
                is HttpException -> {
                    MoviesListViewState.Error(ErrorType.SERVER)
                }
                else -> MoviesListViewState.Error(ErrorType.UNKNOWN)
            }
        }

    }

}
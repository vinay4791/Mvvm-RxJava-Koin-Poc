package com.example.rxjavakoinpoc.movies.di

import com.example.rxjavakoinpoc.movies.MoviesListRepository
import com.example.rxjavakoinpoc.movies.MoviesListViewModel
import com.example.rxjavakoinpoc.movies.adapter.MoviesListAdapter
import com.example.rxjavakoinpoc.movies.backend.MoviesApiFetcher
import com.example.rxjavakoinpoc.movies.backend.MoviesBackend
import com.example.rxjavakoinpoc.movies.viewstate.MoviesListConverter
import com.example.rxjavakoinpoc.rx.AndroidSchedulingStrategyFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val moviesModule = module {

    factory {
        MoviesListAdapter()
    }

    factory {
        atgStockPickupBackend(get())
    }

    factory {
        MoviesApiFetcher(get())
    }

    factory {
        MoviesListRepository(get(), MoviesListConverter(), get(), get(), AndroidSchedulingStrategyFactory.io())
    }

    viewModel {
        MoviesListViewModel(get(), get())
    }
}

fun atgStockPickupBackend(retrofit: Retrofit.Builder): MoviesBackend {
    return retrofit.baseUrl("https://api.themoviedb.org/3/").build().create(MoviesBackend::class.java)
}
package com.example.rxjavakoinpoc.base

import com.example.rxjavakoinpoc.api.networkModule
import com.example.rxjavakoinpoc.db.dbModule
import com.example.rxjavakoinpoc.movies.di.moviesModule

val moviesListAppModules = listOf(
    dbModule,
    networkModule,
    moviesModule
)


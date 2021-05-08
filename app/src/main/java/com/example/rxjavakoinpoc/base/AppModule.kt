package com.example.rxjavakoinpoc.base

import com.example.rxjavakoinpoc.api.networkModule
import com.example.rxjavakoinpoc.db.dbModule
import com.example.rxjavakoinpoc.movies.di.moviesModule
import com.example.rxjavakoinpoc.util.utilsModule

val moviesListAppModules = listOf(
    dbModule,
    utilsModule,
    networkModule,
    moviesModule
)


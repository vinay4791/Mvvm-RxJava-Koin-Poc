package com.example.rxjavakoinpoc.db

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { MovieDb.create(androidContext()) }

}
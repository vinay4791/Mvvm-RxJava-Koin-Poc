package com.example.rxjavakoinpoc

import android.app.Application
import com.example.rxjavakoinpoc.base.moviesListAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesListApplication)
            modules(moviesListAppModules)
        }
    }
}
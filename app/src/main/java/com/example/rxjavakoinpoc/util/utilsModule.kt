package com.example.rxjavakoinpoc.util

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilsModule = module {
    single { Utils(androidContext()) }
}
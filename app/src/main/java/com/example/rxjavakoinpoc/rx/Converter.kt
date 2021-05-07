package com.example.rxjavakoinpoc.rx

import io.reactivex.functions.Function

interface Converter<T, R> : Function<T, R>
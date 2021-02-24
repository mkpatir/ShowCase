package com.mkpatir.showcase.internal.di

import org.koin.dsl.module

val appModule = module(true) {
}

val viewModelModule = module(true) {
}

val appModules = listOf(appModule, viewModelModule)
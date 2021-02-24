package com.mkpatir.showcase.internal.di

import com.mkpatir.showcase.api.AppRepository
import com.mkpatir.showcase.api.AppRepositoryImpl
import com.mkpatir.showcase.api.AppServiceFactory
import org.koin.dsl.module

val appModule = module(true) {

    factory<AppRepository> {
        AppRepositoryImpl(
            get()
        )
    }

    factory {
        AppServiceFactory.buildService()
    }

}

val viewModelModule = module(true) {
}

val appModules = listOf(appModule, viewModelModule)
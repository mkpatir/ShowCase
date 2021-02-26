package com.mkpatir.showcase.internal.di

import com.mkpatir.showcase.api.AppRepository
import com.mkpatir.showcase.api.AppRepositoryImpl
import com.mkpatir.showcase.api.AppServiceFactory
import com.mkpatir.showcase.ui.base.EmptyViewModel
import com.mkpatir.showcase.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
    viewModel { EmptyViewModel() }
    viewModel { HomeViewModel(get()) }
}

val appModules = listOf(appModule, viewModelModule)
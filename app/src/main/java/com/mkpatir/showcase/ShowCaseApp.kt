package com.mkpatir.showcase

import android.app.Application
import com.mkpatir.showcase.internal.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShowCaseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ShowCaseApp)
            modules(appModules)
        }
    }

}
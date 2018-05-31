package com.darja.shurup.di

import android.app.Application
import com.darja.shurup.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
open class AppModule(private val app: Application) {
    protected open fun getDbName() = "logbook"

    @Provides
    @Singleton
    fun providesApp() = app

    @Provides
    @Singleton
    fun providesAppContext(app: App) = app.applicationContext
}
package com.darja.shurup.di

import android.app.Application
import com.darja.shurup.App
import com.darja.shurup.content.ContentReader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
open class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun providesApp() = app

    @Provides
    @Singleton
    fun providesAppContext(app: App) = app.applicationContext

    @Provides
    @Singleton
    fun providesContentReader() = ContentReader(app)
}
package com.darja.shurup.di

import com.darja.shurup.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class
))
interface AppComponent {
    fun inject(app: App)
}

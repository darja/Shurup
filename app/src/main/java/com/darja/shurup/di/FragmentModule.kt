package com.darja.shurup.di

import com.darja.shurup.ui.fragment.topics.TopicsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun topicsFragment(): TopicsFragment
}
package com.darja.shurup.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.darja.shurup.ui.fragment.quiz.QuizViewModel
import com.darja.shurup.ui.fragment.topics.TopicsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TopicsViewModel::class)
    abstract fun bindsTopicsViewModel(vm: TopicsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuizViewModel::class)
    abstract fun bindsQuizViewModel(vm: QuizViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(vmFactory: VMFactory): ViewModelProvider.Factory

}
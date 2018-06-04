package com.darja.shurup.ui.fragment

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel>: Fragment() {

    // view model factory instance
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    // view model instance
    lateinit var viewModel: VM

    protected abstract fun getViewModel(): Class<VM>

    protected abstract fun getActionBarTitle(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
    }

    override fun onStart() {
        super.onStart()
        val currActivity = activity
        (currActivity as AppCompatActivity?)?.supportActionBar?.title = getActionBarTitle()
    }
}
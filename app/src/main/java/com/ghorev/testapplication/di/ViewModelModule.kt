package com.ghorev.testapplication.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ghorev.testapplication.di.ViewModelKey


import com.ghorev.testapplication.ui.first.FirstPageViewModel
import com.ghorev.testapplication.ui.second.SecondPageViewModel
import com.ghorev.testapplication.ui.start.StartPageViewModel
import com.ghorev.testapplication.viewmodel.ViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StartPageViewModel::class)
    abstract fun bindUserViewModel(userViewModel: StartPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FirstPageViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: FirstPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondPageViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: SecondPageViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

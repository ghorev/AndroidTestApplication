package com.ghorev.testapplication.di

import com.ghorev.testapplication.ui.first.FirstPageFragment
import com.ghorev.testapplication.ui.second.SecondPageFragment
import com.ghorev.testapplication.ui.start.StartPageFragment
import com.ghorev.testapplication.ui.timeout.TimeoutFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentContributorsModule {
    @ContributesAndroidInjector
    fun startPageFragment() : StartPageFragment

    @ContributesAndroidInjector
    fun firstPageFragment() : FirstPageFragment

    @ContributesAndroidInjector
    fun secondPageFragment() : SecondPageFragment

    @ContributesAndroidInjector
    fun timeoutFragment() : TimeoutFragment
}
package com.ghorev.testapplication.di

import com.ghorev.testapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityContributorsModule {
    @ContributesAndroidInjector(modules = [FragmentContributorsModule::class])
    @ActivityScope
    fun contributeMainActivity() : MainActivity
}

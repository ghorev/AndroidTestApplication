package com.ghorev.testapplication

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module(includes = [AndroidSupportInjectionModule::class])
interface TestApplicationModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    fun mainActivity() : MainActivity

    @ContributesAndroidInjector
    fun firstPageFragment() : FirstPageFragment

    @Binds
    @Singleton
    fun globalDependency(instance: GlobalDependencyImpl) : GlobalDependency
}

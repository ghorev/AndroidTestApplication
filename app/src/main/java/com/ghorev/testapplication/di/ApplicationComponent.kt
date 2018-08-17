package com.ghorev.testapplication.di

import com.ghorev.testapplication.model.ContentSize
import com.ghorev.testapplication.TestApplication
import com.ghorev.testapplication.navigation.PageId
import com.ghorev.testapplication.navigation.StartPage
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ AndroidSupportInjectionModule::class,
                       ActivityContributorsModule::class,
                       ViewModelModule::class])
interface TestApplicationComponent : AndroidInjector<TestApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TestApplication>() {
        @BindsInstance
        abstract fun withContentSize(@ContentSize size: Int): Builder
        @BindsInstance
        abstract fun withStartPage(@StartPage startPage: PageId): Builder
    }

}

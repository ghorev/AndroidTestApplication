package com.ghorev.testapplication

import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import android.app.Application
import android.support.v4.app.Fragment
import android.util.Log
import dagger.BindsInstance
import dagger.Component
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Singleton

class TestApplication : Application(), HasActivityInjector, HasSupportFragmentInjector
{
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var globalDependency: GlobalDependency

    override fun onCreate() {
        super.onCreate()
        DaggerTestApplicationComponent.builder().contentSize(50).create(this).inject(this)
        Log.d(getString(R.string.app_name), globalDependency.text)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}

@Singleton
@Component(modules = [ AndroidSupportInjectionModule::class, TestApplicationModule::class, MainActivityModule::class ])
interface TestApplicationComponent : AndroidInjector<TestApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TestApplication>() {
        @BindsInstance
        abstract fun contentSize(@ContentSize size: Int): Builder
    }

}

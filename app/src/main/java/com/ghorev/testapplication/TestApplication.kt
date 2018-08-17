package com.ghorev.testapplication

import android.app.Activity
import android.app.Application
import com.ghorev.testapplication.di.ApplicationInjector
import com.ghorev.testapplication.navigation.PageId
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApplication : Application(), HasActivityInjector
{
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        ApplicationInjector.init(this, 50, PageId.START)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}
package com.ghorev.testapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dagger.*
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
        StartPageFragment.OnFragmentInteractionListener,
        SecondPageFragment.OnFragmentInteractionListener,
        UiTimer.Listener
{
    @Inject
    lateinit var activityDependency: MainActivityDependency

    @Inject
    lateinit var otherActivityDependency: OtherMainActivityDependency

    @Inject
    lateinit var globalDependency: GlobalDependency

    @Inject
    lateinit var otherGlobalDependency: OtherGlobalDependency

    @Inject
    lateinit var timer: UiTimer

    private val TIMEOUT_DIALOG_TAG = "TimeoutDialog"

    override fun onFirstPageButtonClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FirstPageFragment.newInstance()).commit()
    }

    override fun onSecondPageButtonClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SecondPageFragment.newInstance()).commit()
    }
    override fun onStartTimerClicked() {
        timer.start(5)
    }

    override fun onTimeout() {
        showTimeoutDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        Log.d(getString(R.string.app_name), "MainActivity::onCreate")
        Log.d(getString(R.string.app_name), globalDependency.text)
        Log.d(getString(R.string.app_name), otherGlobalDependency.text)
        Log.d(getString(R.string.app_name), activityDependency.text)
        Log.d(getString(R.string.app_name), otherActivityDependency.text)
        setContentView(R.layout.activity_main)

        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            Log.d(getString(R.string.app_name), "Create fragment")
            fragment = StartPageFragment.newInstance()
            supportFragmentManager.beginTransaction().
                    add(R.id.fragment_container, fragment).
                    commit()
        }
    }

    override fun onResume() {
        Log.d(getString(R.string.app_name), "onResume")
        super.onResume()
        timer.listner = this
    }

    override fun onPause() {
        Log.d(getString(R.string.app_name), "onPause")
        super.onPause()
        timer.listner = null
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) !is StartPageFragment)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, StartPageFragment.newInstance()).commit()
        else
            super.onBackPressed()
    }

    private fun showTimeoutDialog() {
        if (supportFragmentManager.findFragmentByTag(TIMEOUT_DIALOG_TAG) == null)
            TimeoutFragment().show(supportFragmentManager, TIMEOUT_DIALOG_TAG)
        else
            Log.d(getString(R.string.app_name), "Dialog is already shown")
    }
}

interface MainActivityDependency {
    val text: String
}

@ActivityScope
class MainActivityDependencyImpl @Inject constructor(context: MainActivity) : MainActivityDependency {
    init {
        Log.d(context.getString(R.string.app_name), "Create MainActivityDependency")
    }

    override val text: String = context.componentName.className
}

@ActivityScope
class OtherMainActivityDependency @Inject constructor(context: MainActivity) {
    init {
        Log.d(context.getString(R.string.app_name), "Create OtherMainActivityDependency")
    }

    val text: String = context.componentName.shortClassName
}

@Module
interface MainActivityModule {
    @Binds
    @ActivityScope
    fun dependency(instance: MainActivityDependencyImpl) : MainActivityDependency
}

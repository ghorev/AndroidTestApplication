package com.ghorev.testapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(),
        StartPageFragment.OnFragmentInteractionListener,
        FirstPageFragment.OnFragmentInteractionListener,
        SecondPageFragment.OnFragmentInteractionListener
{
    private val TIMEOUT_DIALOG_TAG = "TimeoutDialog"

    override fun onFirstPageButtonClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FirstPageFragment.newInstance()).commit()
    }

    override fun onSecondPageButtonClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SecondPageFragment.newInstance()).commit()
    }
    override fun onStartTimerClicked() {
        Timers.add(5)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(getString(R.string.app_name), "MainActivity::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = StartPageFragment.newInstance()
            supportFragmentManager.beginTransaction().
                    add(R.id.fragment_container, fragment).
                    commit()
        }
    }

    override fun onResume() {
        Log.d(getString(R.string.app_name), "onResume")
        super.onResume()
        Timers.activity = this
    }

    override fun onPause() {
        Log.d(getString(R.string.app_name), "onPause")
        super.onPause()
        Timers.activity = null
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

    private companion object Timers {
        private var skipped = false
        var activity: MainActivity? = null
            set(it) {
                field = it
                if (it != null && skipped) {
                    activity!!.showTimeoutDialog()
                }
                skipped = false
            }

        fun add(secs: Long) {
            Observable.interval(1, TimeUnit.SECONDS).
                    take(secs).
                    subscribeOn(AndroidSchedulers.mainThread()).
                    subscribe({}, {}, {
                        if (activity != null)
                            activity!!.showTimeoutDialog()
                        else
                            skipped = true
                    })
        }
    }
}

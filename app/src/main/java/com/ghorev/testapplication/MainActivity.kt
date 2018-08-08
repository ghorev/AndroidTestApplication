package com.ghorev.testapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity(),
        StartPageFragment.OnFragmentInteractionListener,
        FirstPageFragment.OnFragmentInteractionListener,
        SecondPageFragment.OnFragmentInteractionListener
{
    override fun onFirstPageButtonClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FirstPageFragment.newInstance()).commit()
    }
    override fun onSecondPageButtonClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SecondPageFragment.newInstance()).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) !is StartPageFragment)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, StartPageFragment.newInstance()).commit()
        else
            super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TestApplication", "MainActivity::onCreate")
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
}

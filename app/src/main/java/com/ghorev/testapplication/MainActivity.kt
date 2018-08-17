package com.ghorev.testapplication

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ghorev.testapplication.navigation.NavigationController
import com.ghorev.testapplication.navigation.PageId
import com.ghorev.testapplication.ui.first.FirstPageFragment
import com.ghorev.testapplication.ui.second.SecondPageFragment
import com.ghorev.testapplication.ui.start.StartPageFragment
import com.ghorev.testapplication.ui.timeout.TimeoutFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
        HasSupportFragmentInjector
{
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigation: NavigationController

    private val TIMEOUT_DIALOG_TAG = "TimeoutDialog"

    private val navigationFragment = object: Fragment() {
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

        }
    }

    override fun supportFragmentInjector() = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.currentFrame.observe(this, Observer<PageId> { t ->
            t?.let {
                showPage(it)
            }
        })

        navigation.showDialog.observe(this, Observer<Boolean>{ t ->
            t?.let {
                if (t) {
                    showDialog()
                }
            }
        })

        navigation.currentFrame.value?.let {
            showPage(it)
        }

        if (navigation.showDialog.value != false) {
            showDialog()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) !is StartPageFragment)
            navigation.currentFrame.value = PageId.START
        else
            super.onBackPressed()
    }

    private fun showDialog() {
        if (supportFragmentManager.findFragmentByTag(TIMEOUT_DIALOG_TAG) == null) {
            TimeoutFragment().show(supportFragmentManager, TIMEOUT_DIALOG_TAG)
        }
    }

    private fun showPage(page: PageId) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            supportFragmentManager.beginTransaction().
                    add(R.id.fragment_container, createFragment(page)).
                    commit()
        } else {
            supportFragmentManager.beginTransaction().
                    replace(R.id.fragment_container, createFragment(page)).
                    commit()
        }
        navigation.currentFrame.value = null
    }

    private fun createFragment(page: PageId) : Fragment =
        when(page) {
            PageId.START -> StartPageFragment.newInstance()
            PageId.FIRST -> FirstPageFragment.newInstance()
            PageId.SECOND -> SecondPageFragment.newInstance()
        }
}

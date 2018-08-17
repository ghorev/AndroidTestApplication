package com.ghorev.testapplication.ui.second

import android.arch.lifecycle.ViewModel
import com.ghorev.testapplication.navigation.NavigationController
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SecondPageViewModel  @Inject constructor() : ViewModel() {
    @Inject
    lateinit var navigationController: NavigationController

    fun onStartTimerClicked() {
        Completable.timer(5, TimeUnit.SECONDS).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe {
                    navigationController.showDialog.value = true
                }
    }

}


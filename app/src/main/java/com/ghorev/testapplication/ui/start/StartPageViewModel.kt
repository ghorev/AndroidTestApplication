package com.ghorev.testapplication.ui.start

import android.arch.lifecycle.ViewModel
import com.ghorev.testapplication.di.Injectable
import com.ghorev.testapplication.navigation.NavigationController
import com.ghorev.testapplication.navigation.PageId
import javax.inject.Inject

class StartPageViewModel @Inject constructor(val navigation: NavigationController) : ViewModel() {
    fun onFirstPageClicked() {
        navigation.currentFrame.value = PageId.FIRST
    }

    fun onSecondPageClicked() {
        navigation.currentFrame.value = PageId.SECOND
    }
}


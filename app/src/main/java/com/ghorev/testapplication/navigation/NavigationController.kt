package com.ghorev.testapplication.navigation

import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

enum class PageId {
    START, FIRST, SECOND
}

annotation class StartPage

@Singleton
class NavigationController @Inject constructor(@StartPage startPage: PageId) {
    var currentFrame = MutableLiveData<PageId>()
    var showDialog = MutableLiveData<Boolean>()

    init {
        currentFrame.value = startPage
        showDialog.value = false
    }
}


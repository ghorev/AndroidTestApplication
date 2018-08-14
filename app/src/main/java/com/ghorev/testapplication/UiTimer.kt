package com.ghorev.testapplication

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiTimer @Inject constructor(){
    var listner: Listener? = null
    set(value) {
        field = value
        field?.let {
            if (skipped) it.onTimeout()
        }
        skipped = false
    }

    private var skipped = false

    fun start(secs: Long) {
        Completable.timer(secs, TimeUnit.SECONDS).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe {
                    if (listner != null)
                        listner!!.onTimeout()
                    else
                        skipped = true
                }
    }

    interface Listener {
        fun onTimeout()
    }
}
package com.ghorev.testapplication

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

interface GlobalDependency {
    val text: String
}

class GlobalDependencyImpl @Inject constructor(context: TestApplication): GlobalDependency {
    init {
        Log.d(context.getString(R.string.app_name), "Create GlobalDependency")
    }

    override val text: String = context.applicationInfo.processName
}

@Singleton
class OtherGlobalDependency @Inject constructor(context: TestApplication) {
    init {
        Log.d(context.getString(R.string.app_name), "Create OtherGlobalDependency")
    }

    val text: String = context.applicationInfo.className
}


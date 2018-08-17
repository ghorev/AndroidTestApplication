package com.ghorev.testapplication.model

import android.util.Log
import com.ghorev.testapplication.R
import com.ghorev.testapplication.TestApplication
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

annotation class ContentSize

@Singleton
class Content @Inject constructor(context: TestApplication, @ContentSize size: Int) {
    open class Record(var text: String, var date: Date)

    private var content = mutableListOf<Record>()

    init {
        Log.d(context.getString(R.string.app_name), "Create Content")
        val date = Date()
        for (i in 1..size)
            content.add(Record((1..i).joinToString(" - "), date))
    }

    val size: Int
        get() = content.size

    fun getRecord(i: Int) = content[i]
}

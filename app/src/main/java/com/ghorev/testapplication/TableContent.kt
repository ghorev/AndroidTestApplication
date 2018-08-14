package com.ghorev.testapplication

import android.util.Log
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

annotation class ContentSize

@Singleton
class TableContent @Inject constructor(@ContentSize context: TestApplication, size: Int = 50) {
    open class Record(var text: String, var date: Date)

    private var content = mutableListOf<Record>()

    init {
        Log.d(context.getString(R.string.app_name), "Create TableContent")
        val date = Date()
        for (i in 1..size)
            content.add(Record((1..i).joinToString(" - "), date))
    }

    val size: Int
        get() = content.size

    fun getRecord(i: Int) = content[i]
}

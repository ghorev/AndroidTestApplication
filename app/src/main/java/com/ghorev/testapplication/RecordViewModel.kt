package com.ghorev.testapplication

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.format.DateFormat
import java.util.*

class RecordViewModel(record: TableContent.Record) : BaseObservable() {
    var record = record
    set(value) {
        field = value
        notifyChange()
    }

    @Bindable
    fun getText() = record.text
    @Bindable
    fun getDate(): String = DateFormat.format("dd/MM/yyyy hh:mm:ss", record.date).toString()
}
package com.ghorev.testapplication

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.fragment_timeout.view.*

class TimeoutFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.fragment_timeout, null)
        view.timeout_text.setText(R.string.timeout_text)

        return AlertDialog.Builder(activity!!).
                setView(view).
                setTitle(R.string.timeout_title).
                setPositiveButton(R.string.ok, null).
                create()
    }

}

package com.ghorev.testapplication.ui.timeout

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.ghorev.testapplication.R
import com.ghorev.testapplication.di.Injectable
import com.ghorev.testapplication.navigation.NavigationController
import com.ghorev.testapplication.ui.second.SecondPageFragment
import kotlinx.android.synthetic.main.fragment_timeout.view.*
import javax.inject.Inject

class TimeoutFragment : DialogFragment(), Injectable {
    @Inject
    lateinit var navigation: NavigationController

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.fragment_timeout, null)
        view.timeout_text.setText(R.string.timeout_text)

        return AlertDialog.Builder(activity!!).
                setView(view).
                setTitle(R.string.timeout_title).
                setPositiveButton(R.string.ok) { _, _ ->
                    navigation.showDialog.value = false
                }.
                create()
    }
}

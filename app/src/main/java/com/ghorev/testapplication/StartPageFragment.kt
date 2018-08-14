package com.ghorev.testapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_start_page.*
import kotlinx.android.synthetic.main.fragment_start_page.view.*

class StartPageFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start_page, container, false).apply {
            page1_button.setOnClickListener { listener?.onFirstPageButtonClicked() }
            page2_button.setOnClickListener { listener?.onSecondPageButtonClicked() }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFirstPageButtonClicked()
        fun onSecondPageButtonClicked()
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartPageFragment()
    }
}

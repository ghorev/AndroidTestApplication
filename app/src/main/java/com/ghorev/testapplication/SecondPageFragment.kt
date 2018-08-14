package com.ghorev.testapplication

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghorev.testapplication.databinding.FragmentSecondPageBinding
import kotlinx.android.synthetic.main.fragment_second_page.view.*

class SecondPageFragment : Fragment() {

    private lateinit var bindings: FragmentSecondPageBinding
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.fragment_second_page, container, false)
        bindings.listener = listener
        return bindings.root
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
        fun onStartTimerClicked()
    }


    companion object {
        @JvmStatic
        fun newInstance() = SecondPageFragment()
    }
}

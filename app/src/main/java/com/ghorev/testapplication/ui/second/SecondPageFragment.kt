package com.ghorev.testapplication.ui.second

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghorev.testapplication.R
import com.ghorev.testapplication.databinding.FragmentSecondPageBinding
import com.ghorev.testapplication.di.Injectable
import com.ghorev.testapplication.viewmodel.ViewModelFactory
import javax.inject.Inject

class SecondPageFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var bindings: FragmentSecondPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.fragment_second_page, container, false)
        bindings.viewModel = ViewModelProviders.
                of(this, viewModelFactory).
                get(SecondPageViewModel::class.java)
        return bindings.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SecondPageFragment()
    }
}

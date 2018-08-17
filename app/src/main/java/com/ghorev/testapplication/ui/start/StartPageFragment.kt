package com.ghorev.testapplication.ui.start

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghorev.testapplication.R
import com.ghorev.testapplication.databinding.FragmentStartPageBinding
import com.ghorev.testapplication.di.Injectable
import com.ghorev.testapplication.viewmodel.ViewModelFactory
import javax.inject.Inject

class StartPageFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: StartPageViewModel

    private lateinit var binding: FragmentStartPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_page, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StartPageViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartPageFragment()
    }
}

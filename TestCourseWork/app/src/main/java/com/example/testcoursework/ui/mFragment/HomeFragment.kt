package com.example.testcoursework.ui.mFragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testcoursework.R
import com.example.testcoursework.databinding.HomeFragmentBinding
import com.example.testcoursework.viewmodel.HomeViewModel


class HomeFragment : Fragment()
{
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        val view: View = binding.root
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel.currentWeight.set(2.4f)
        Log.d("onActivityCreated", "${viewModel.currentWeight.get()}")
        // TODO: Use the ViewModel
    }

}

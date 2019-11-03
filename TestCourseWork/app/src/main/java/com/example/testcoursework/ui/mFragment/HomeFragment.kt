package com.example.testcoursework.ui.mFragment

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testcoursework.R
import com.example.testcoursework.databinding.HomeFragmentBinding
import com.example.testcoursework.utils.googleAccount.GoogleAccount
import com.example.testcoursework.viewmodel.HomeViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field


class HomeFragment : Fragment()
{
    companion object {
        private const val LOG_TAG = "HomeFragmentTag"
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
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        with(viewModel)
        {
            getStepCount()
            getCoveredDistance()
            getCalories()
        }
        // TODO: Use the ViewModel
    }

    override fun onResume()
    {
        with(viewModel)
        {
            getStepCount()
            getCoveredDistance()
            getCalories()
        }
        super.onResume()
    }
}

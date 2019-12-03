package com.example.testcoursework.ui.mFragment

import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.system.Os
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Observer
import com.example.testcoursework.R
import com.example.testcoursework.databinding.HomeFragmentBinding
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.utils.googleAccount.GoogleAccount
import com.example.testcoursework.viewModel.HomeViewModel


class HomeFragment : Fragment()
{
    companion object {
        private const val LOG_TAG = "HomeFragmentTag"
    }
    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        activity?.window?.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = getColor(context, R.color.white)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        val view: View = binding.root
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        observe()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
    }

    private fun observe()
    {
        Singleton.person.name.observe(this, Observer<String?> {
            viewModel.name.set(it)
        })
        Singleton.person.photoUrl.observe(this, Observer<Uri?> {
            viewModel.photoUrl.set(it)
        })
        Singleton.personActivity.calories.observe(this, Observer<Int> {
            viewModel.calories.set(it)
        })
        Singleton.personActivity.countOfDrunkWater.observe(this, Observer<Int> {
            viewModel.countOfDrunkWater.set(it)
        })
        Singleton.personActivity.coveredDistance.observe(this, Observer<Float> {
            viewModel.coveredDistance.set(it)
        })
        Singleton.personActivity.numberOfSteps.observe(this, Observer<Int> {
            viewModel.numberOfSteps.set(it)
        })
        Singleton.person.weight.observe(this, Observer<Int> {
            viewModel.currentWeight.set(it)
        })
    }
    override fun onResume()
    {
        GoogleAccount.retry(context!!)
        super.onResume()
    }
}

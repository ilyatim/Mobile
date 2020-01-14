package com.example.testcoursework.ui.mFragment

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Observer
import com.example.testcoursework.R
import com.example.testcoursework.databinding.HomeFragmentBinding
import com.example.testcoursework.data.model.personInfo.Singleton
import com.example.testcoursework.data.repo.googleAccount.GoogleAccount
import com.example.testcoursework.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    companion object {
        private const val LOG_TAG = "HomeFragmentTag"
    }

    private var firstInit = true
    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = getColor(context, R.color.white)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        val view: View = binding.root
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        observe()
        super.onActivityCreated(savedInstanceState)
    }
    override fun onResume() {
        GoogleAccount.retry(context!!)
        super.onResume()
    }
    private fun observe() {
        Singleton.person.name.observe(this, Observer {
            viewModel.name.set(it)
        })
        Singleton.person.photoUrl.observe(this, Observer {
            viewModel.photoUrl.set(it)
        })
        Singleton.personActivity.calories.observe(this, Observer<Int> {
            val lastValue = viewModel.calories.get()!!
            viewModel.animateTextView(binding.caloriesLayout.calories, lastValue, it)
        })
        Singleton.personActivity.countOfDrunkWater.observe(this, Observer<Int> {
            viewModel.countOfDrunkWater.set(it)
        })
        Singleton.personActivity.coveredDistance.observe(this, Observer<Float> {
            val lastValue = viewModel.coveredDistance.get()
            viewModel.animateTextView(binding.distanceLayout.distance, lastValue, it)
        })
        Singleton.personActivity.numberOfSteps.observe(this, Observer<Int> {
            val lastValue = viewModel.numberOfSteps.get()!!
            viewModel.animateTextView(binding.stepsLayout.steps, lastValue, it)
            viewModel.animateProgressBar(binding.periodOfActivityLayout.progressBar, lastValue, it)
        })
        Singleton.person.weight.observe(this, Observer<Int> {
            val lastValue = viewModel.currentWeight.get()
            viewModel.animateTextView(binding.weightLayout.weight, lastValue, it)
        })
    }
}

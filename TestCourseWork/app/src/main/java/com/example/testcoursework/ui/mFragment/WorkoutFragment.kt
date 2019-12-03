package com.example.testcoursework.ui.mFragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import com.example.testcoursework.R
import com.example.testcoursework.databinding.WorkoutFragmentBinding
import com.example.testcoursework.viewModel.WorkoutViewModel


class WorkoutFragment : Fragment()
{

    companion object {
        fun newInstance() = WorkoutFragment()
    }

    private lateinit var binding: WorkoutFragmentBinding
    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        viewModel = ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
        activity?.window?.apply {
            decorView.systemUiVisibility = 0
            statusBarColor = getColor(context, R.color.top_bar_background)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.workout_fragment, container, false)
        val view: View = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
}

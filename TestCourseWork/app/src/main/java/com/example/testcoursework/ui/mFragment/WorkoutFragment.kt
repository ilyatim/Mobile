package com.example.testcoursework.ui.mFragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import com.example.testcoursework.R
import com.example.testcoursework.databinding.WorkoutFragmentBinding
import com.example.testcoursework.viewModel.WorkoutViewModel


class WorkoutFragment : Fragment()
{
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
        createAnimation()
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return view
    }
    private fun createAnimation()
    {
        shadowViewAnim()
    }
    private fun shadowViewAnim()
    {
        val anim = AnimationUtils.loadAnimation(context, R.anim.lowering_curtain_workout_fragment)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?)
            {
                binding.historyTextView.alpha = 0f
            }
            override fun onAnimationEnd(animation: Animation?)
            {
                binding.historyTextView.animate().alpha(1f).setDuration(1000).setListener(null)
            }
            override fun onAnimationStart(animation: Animation?) {}
        })
        binding.historyOfTrainingView.startAnimation(anim)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
}

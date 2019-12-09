package com.example.testcoursework.ui.mFragment

import android.content.Context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat.getColor
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.afollestad.materialdialogs.list.toggleItemChecked
import com.example.testcoursework.R
import com.example.testcoursework.databinding.PersonFragmentBinding
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.ui.activity.*
import com.example.testcoursework.viewModel.PersonViewModel
import kotlinx.android.synthetic.main.number_picker_dialog.*


class PersonFragment : Fragment() {
    private lateinit var binding: PersonFragmentBinding
    private lateinit var viewModel: PersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        activity?.window?.apply {
            decorView.systemUiVisibility = 0
            statusBarColor = getColor(context, R.color.top_bar_background)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.person_fragment, container, false)
        val view: View = binding.root
        binding.viewModel = viewModel
        createAnimation()
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        observe()
        super.onActivityCreated(savedInstanceState)
    }
    private fun createAnimation() {
        val anim = AnimationUtils.loadAnimation(context, R.anim.lowering_curtain_person_fragment)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.profileImagePersonFragment.alpha = 0f
                binding.linearLayoutPersonInfo.alpha = 0f
            }
            override fun onAnimationEnd(animation: Animation?) {
                binding.profileImagePersonFragment.animate()
                        .alpha(1f)
                        .setDuration(1000)
                        .setListener(null)
                binding.linearLayoutPersonInfo.animate()
                        .alpha(1f)
                        .setDuration(1000)
                        .setListener(null)
            }
            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        binding.personInfoView.startAnimation(anim)
    }
    private fun observe() {
        val context = this
        viewModel.uiEventLiveData.observe(this, Observer {
            //TODO rewrite start activity 1-3
            when (it) {
                1 -> {
                    context.startActivity(Intent(activity , ProgressActivity::class.java))
                }
                3 -> {
                    context.startActivity(Intent(activity , ReportActivity::class.java))
                }
                4 -> {
                    context.startActivity(Intent(activity , InformationActivity::class.java))
                }
            }
        })
        Singleton.person.fullName.observe(this, Observer {
            viewModel.fullName.set(it)
        })
        Singleton.person.email.observe(this, Observer {
            viewModel.email.set(it)
        })
        Singleton.person.weight.observe(this, Observer<Int> {
            viewModel.weight.set(it)
        })
        Singleton.person.height.observe(this, Observer<Int> {
            viewModel.height.set(it)
        })
        Singleton.person.gender.observe(this, Observer {
            viewModel.gender.set(it.value)
        })
    }
}

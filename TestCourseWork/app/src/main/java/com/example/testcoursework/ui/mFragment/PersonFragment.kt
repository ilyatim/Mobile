package com.example.testcoursework.ui.mFragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import com.example.testcoursework.R
import com.example.testcoursework.databinding.PersonFragmentBinding
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.ui.activity.*
import com.example.testcoursework.viewModel.PersonViewModel


class PersonFragment : Fragment()
{
    private lateinit var binding: PersonFragmentBinding
    private lateinit var viewModel: PersonViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        viewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        activity?.window?.apply {
            decorView.systemUiVisibility = 0
            statusBarColor = getColor(context, R.color.top_bar_background)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.person_fragment, container, false)
        val view: View = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        val context = this
        viewModel.uiEventLiveData.observe(this, Observer {
            when (it) {
                1 -> { context.startActivity(Intent(activity , ProgressActivity::class.java)) }
                2 -> { context.startActivity(Intent(activity , ProfileActivity::class.java)) }
                3 -> { context.startActivity(Intent(activity , ReportActivity::class.java)) }
                4 -> { context.startActivity(Intent(activity , InformationActivity::class.java)) }
            }
        })
        Singleton.person.fullName.observe(this, Observer {
            viewModel.fullName.set(it)
        })
        Singleton.person.email.observe(this, Observer {
            viewModel.email.set(it)
        })
    }

}

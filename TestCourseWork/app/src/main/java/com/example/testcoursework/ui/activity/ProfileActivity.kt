package com.example.testcoursework.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityProfileBinding
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.ui.dialog.ChangeGenderDialog
import com.example.testcoursework.viewModel.ProfileViewModel

class ProfileActivity : AppCompatActivity()
{
    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.supportFragmentManager = supportFragmentManager
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        observe()
    }
    private fun observe()
    {
        Singleton.personActivity.person.value?.weight?.observe(this, Observer<Float> {
            viewModel.weight.set(it)
        })
        Singleton.personActivity.person.value?.height?.observe(this, Observer<Int> {
            viewModel.height.set(it)
        })
        Singleton.personActivity.person.value?.gender?.observe(this, Observer<String> {
            viewModel.gender.set(it)
        })
    }
}

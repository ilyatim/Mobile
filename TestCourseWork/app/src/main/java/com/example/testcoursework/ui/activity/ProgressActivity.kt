package com.example.testcoursework.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityProgressBinding
import com.example.testcoursework.viewModel.ProgressActivityViewModel

class ProgressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgressBinding
    private lateinit var viewModel: ProgressActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_progress)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(ProgressActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}

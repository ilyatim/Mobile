package com.example.testcoursework.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityReportBinding
import com.example.testcoursework.viewModel.ProgressActivityViewModel
import com.example.testcoursework.viewModel.ReportActivityViewModel

class ReportActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityReportBinding
    private lateinit var viewModel: ReportActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_report)
        viewModel = ViewModelProviders.of(this).get(ReportActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}

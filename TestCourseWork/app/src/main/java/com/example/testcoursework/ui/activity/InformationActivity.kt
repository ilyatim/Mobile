package com.example.testcoursework.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityInformationBinding
import com.example.testcoursework.viewModel.InformationViewModel

class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding
    private lateinit var viewModel: InformationViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_information)
        viewModel = ViewModelProviders.of(this).get(InformationViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}

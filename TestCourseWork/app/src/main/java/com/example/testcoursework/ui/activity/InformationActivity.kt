package com.example.testcoursework.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityInformationBinding
import com.example.testcoursework.utils.swipeListener.OnSwipeTouchListener
import com.example.testcoursework.viewModel.InformationViewModel

class InformationActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityInformationBinding
    private lateinit var viewModel: InformationViewModel


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_information)
        viewModel = ViewModelProviders.of(this).get(InformationViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.linearLayoutActivityInfo.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            override fun onSwipeTop() { super.onSwipeTop() }
            override fun onSwipeBottom() { super.onSwipeBottom() }
            override fun onSwipeLeft() { super.onSwipeLeft() }
            override fun onSwipeRight()
            {
                finish()
                super.onSwipeRight()
            }
        })

        supportActionBar?.apply {
            setTitle(R.string.informationStringRes)
        }
    }
}

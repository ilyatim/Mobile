package com.example.testcoursework.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.MenuItem
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityMainBinding
import com.example.testcoursework.ui.mFragment.HomeFragment
import com.example.testcoursework.ui.mFragment.PersonFragment
import com.example.testcoursework.ui.mFragment.WorkoutFragment

class MyViewModel(app: Application) : AndroidViewModel(app)
{

}
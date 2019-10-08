package com.example.testcoursework.ui.activity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.BindingAdapter
import android.databinding.BindingMethod
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityMainBinding
import com.example.testcoursework.ui.mFragment.HomeFragment
import com.example.testcoursework.ui.mFragment.PersonFragment
import com.example.testcoursework.ui.mFragment.WorkoutFragment
import com.example.testcoursework.viewmodel.MyViewModel

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        when (item.itemId)
        {
            R.id.navigation_home -> {
                transaction.replace(R.id.fragment, HomeFragment())
            }
            R.id.navigation_workout -> {

                transaction.replace(R.id.fragment, WorkoutFragment())
            }
            R.id.navigation_about_me -> {
                transaction.replace(R.id.fragment, PersonFragment())
            }
        }
        transaction.commit()
        return@OnNavigationItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(R.id.fragment, HomeFragment()).commit()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}

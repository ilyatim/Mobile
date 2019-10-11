package com.example.testcoursework.ui.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.databinding.BindingAdapter
import android.databinding.BindingMethod
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.testcoursework.R
import com.example.testcoursework.data.PersonActivity
import com.example.testcoursework.data.Singleton
import com.example.testcoursework.databinding.ActivityMainBinding
import com.example.testcoursework.ui.mFragment.HomeFragment
import com.example.testcoursework.ui.mFragment.PersonFragment
import com.example.testcoursework.ui.mFragment.WorkoutFragment
import com.example.testcoursework.viewmodel.MyViewModel

class MainActivity : AppCompatActivity()
{
    private val PERSON_ACTIVITY_STRING: String = "person_activity"
    private lateinit var pref: SharedPreferences
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

        pref = getSharedPreferences("com.example.testcoursework", Context.MODE_PRIVATE)
        if(pref.contains(PERSON_ACTIVITY_STRING))
        {
            Singleton.personActivity = pref.getPersonActivityData(PERSON_ACTIVITY_STRING)
        }

        Log.d("OnCreate", pref.contains(PERSON_ACTIVITY_STRING).toString())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)


    }

    override fun onDestroy()
    {
        Log.d("OnDestroy", "work")
        val e = pref.edit()
        e.put(PERSON_ACTIVITY_STRING, Singleton.personActivity)
        e.apply()
        super.onDestroy()
    }
    private fun SharedPreferences.Editor.put(string: String, person: PersonActivity)
    {
        putString(string, "PersonActivity")
        putInt("Number_of_drunk_water_+$string", person.countOfDrunkWater)
        putFloat("Covered_distance_+$string", person.coveredDistance)
        putInt("Number_of_steps_+$string", person.numberOfSteps)
        putFloat("Current_weight_+$string", person.currentWeight)
    }
    private fun SharedPreferences.getPersonActivityData(string: String): PersonActivity
    {
        val countOfDrunkWater = getInt("Number_of_drunk_water_+$string", 0)
        val coveredDistance = getFloat("Covered_distance_+$string", 0.0f)
        val numberOfSteps = getInt("Number_of_steps_+$string", 0)
        val currentWeight = getFloat("Current_weight_+$string", 0.0f)
        return PersonActivity(countOfDrunkWater, coveredDistance, numberOfSteps, currentWeight)
    }
}

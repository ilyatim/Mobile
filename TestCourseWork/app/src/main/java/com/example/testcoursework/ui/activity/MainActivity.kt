package com.example.testcoursework.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityMainBinding
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.model.dataUtil.DataProcessing
import com.example.testcoursework.ui.mFragment.HomeFragment
import com.example.testcoursework.ui.mFragment.PersonFragment
import com.example.testcoursework.ui.mFragment.WorkoutFragment
import com.example.testcoursework.utils.googleAccount.GoogleAccount
import com.example.testcoursework.viewModel.MyViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    companion object {
        private var currentFragment: Int = -1
        private const val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1//System.identityHashCode(this).compareTo(0xFFFF)
        private const val GOOGLE_PROFILE_PERMISSION_REQUEST_CODE = 2//
        private const val LOG_TAG: String = "CheckMainActivity"
    }

    private lateinit var viewModel: MyViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var fitnessOptions: FitnessOptions

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        when(item.itemId)
        {
            R.id.navigation_home -> {
                if(currentFragment != 1)
                {
                    transaction.replace(R.id.fragment, HomeFragment())
                    currentFragment = 1
                    transaction.commit()
                }
            }
            R.id.navigation_workout -> {
                if(currentFragment != 2)
                {
                    transaction.replace(R.id.fragment, WorkoutFragment())
                    currentFragment = 2
                    transaction.commit()
                }
            }
            R.id.navigation_about_me -> {
                if(currentFragment != 3)
                {
                    transaction.replace(R.id.fragment, PersonFragment())
                    currentFragment = 3
                    transaction.commit()
                }
            }
        }
        return@OnNavigationItemSelectedListener true
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        DataProcessing.loadData(DataProcessing.getSharedPreferences(this))

        supportFragmentManager.beginTransaction().add(R.id.fragment, HomeFragment()).commit()

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        fitnessInit()
        GoogleAccount.getPersonActivity(this)

        GoogleAccount.getPersonInfo(this)

        initGoogleProfilePermission()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if(requestCode == Activity.RESULT_OK)
        {
            if(requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
            {
                viewModel.accessGoogleFit()
            }
            if(requestCode == GOOGLE_PROFILE_PERMISSION_REQUEST_CODE)
            {
                Log.d("MyActivity", GoogleAccount.getLastSignInAccount(this)?.email)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun initGoogleProfilePermission()
    {
        if(!GoogleSignIn.hasPermissions(GoogleAccount.getLastSignInAccount(this), Scope(Scopes.PROFILE)))
        {
            GoogleSignIn.requestPermissions(this, GOOGLE_PROFILE_PERMISSION_REQUEST_CODE, GoogleSignIn.getLastSignedInAccount(this), Scope(Scopes.PROFILE))
        }
    }
    private fun fitnessInit()
    {
        fitnessOptions = com.example.testcoursework.utils.mFitness.Fitness.fitnessInit()
        if(!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions))
        {
            requestPermission(fitnessOptions, GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
        }
        else
        {
            viewModel.accessGoogleFit()
        }
    }
    private fun requestPermission(fitnessOptions: FitnessOptions, requestCode: Int)
    {
        GoogleSignIn.requestPermissions(
            this,
            requestCode,
            GoogleSignIn.getLastSignedInAccount(this),
            fitnessOptions
        )
    }
    override fun onDestroy()
    {
        DataProcessing.saveData(Singleton.personActivity)
        super.onDestroy()
    }

}

package com.example.testcoursework.ui.activity

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.testcoursework.R
import com.example.testcoursework.data.PersonActivity
import com.example.testcoursework.data.Singleton
import com.example.testcoursework.databinding.ActivityMainBinding
import com.example.testcoursework.ui.mFragment.HomeFragment
import com.example.testcoursework.ui.mFragment.PersonFragment
import com.example.testcoursework.ui.mFragment.WorkoutFragment
import com.example.testcoursework.viewmodel.MyViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessActivities
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.HistoryClient
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.result.DataReadResponse
import com.google.android.gms.tasks.Task
import org.koin.core.module.Module
import org.koin.dsl.module
import java.text.DateFormat.getTimeInstance
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity()
{
    companion object
    {
        private val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1//System.identityHashCode(this).compareTo(0xFFFF)
        private val LOG_TAG: String = "CheckMainActivity"
        private val PERSON_ACTIVITY_STRING: String = "person_activity"
    }


    private lateinit var viewModel: MyViewModel
    private lateinit var pref: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private lateinit var fitnessOptions: FitnessOptions
    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInAccount: GoogleSignInClient

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

        loadData()

        supportFragmentManager.beginTransaction().add(R.id.fragment, HomeFragment()).commit()

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            binding.viewModel = viewModel
            binding.executePendingBindings()
            binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }

        /*gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInAccount = GoogleSignIn.getClient(this, gso)
        val signInIntent = mGoogleSignInAccount.signInIntent
        startActivityForResult(signInIntent, GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)*/
        fitnessInit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if(requestCode == Activity.RESULT_OK)
        {
            if(requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
            {
                //val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                //handleSignInResult(task)
                this.accessGoogleFit()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun fitnessInit()
    {
        fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .build()
        if(!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions))
        {
            requestPermission(fitnessOptions, GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
        }
        else
        {
            accessGoogleFit()
        }
    }
    private fun requestPermission(fitnessOptions: FitnessOptions, requestCode: Int)
    {
        GoogleSignIn.requestPermissions(
            this,
            requestCode,
            GoogleSignIn.getLastSignedInAccount(this),
            fitnessOptions)
    }
    private fun accessGoogleFit()
    {
        val cal = Calendar.getInstance()
        cal.time = Date()

        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        val endTime = cal.timeInMillis

        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        val startTime = cal.timeInMillis


        val readRequest = DataReadRequest.Builder()
            .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
            .bucketByTime(1, TimeUnit.DAYS)
            .build()

        val task = GoogleSignIn.getLastSignedInAccount(this)?.let {
            Fitness.getHistoryClient(this, it)
                .readData(readRequest)
                .addOnSuccessListener {
                    Log.d(LOG_TAG, "onSuccess()")
                }
                .addOnFailureListener { e -> Log.e(LOG_TAG, "onFailure()", e); }
                .addOnCompleteListener { Log.d(LOG_TAG, "onComplete()"); }
        }
    }
    private fun dumpDataSet(dataSet: DataSet)
    {
        Log.i(LOG_TAG, "Data returned for Data type: " + dataSet.dataType.name)

        val dateFormat = getTimeInstance()

        for (dp in dataSet.dataPoints) {
            Log.i(LOG_TAG, "Data point:")
            Log.i(LOG_TAG, "\tType: " + dp.dataType.name)
            Log.i(LOG_TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)))
            Log.i(LOG_TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)))
            for (field in dp.dataType.fields) {
                Log.i(LOG_TAG, "\tField: " + field.name + " Value: " + dp.getValue(field))
            }
        }
    }
    override fun onDestroy()
    {
        val e = pref.edit()
        e.put(PERSON_ACTIVITY_STRING, Singleton.personActivity)
        e.apply()
        super.onDestroy()
    }
    private fun loadData()
    {
        pref = getSharedPreferences("com.example.testcoursework", Context.MODE_PRIVATE)
        if(pref.contains(PERSON_ACTIVITY_STRING))
        {
            Singleton.personActivity = pref.getPersonActivityData(PERSON_ACTIVITY_STRING)
        }
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

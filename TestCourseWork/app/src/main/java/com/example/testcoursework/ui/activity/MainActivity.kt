package com.example.testcoursework.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.afollestad.materialdialogs.list.toggleItemChecked
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityMainBinding
import com.example.testcoursework.data.model.personInfo.Singleton
import com.example.testcoursework.data.model.classes.enumClass.Gender
import com.example.testcoursework.data.repo.DataProcessing
import com.example.testcoursework.ui.mFragment.HomeFragment
import com.example.testcoursework.ui.mFragment.PersonFragment
import com.example.testcoursework.ui.mFragment.WorkoutFragment
import com.example.testcoursework.data.repo.googleAccount.GoogleAccount
import com.example.testcoursework.viewModel.MyViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.number_picker_dialog.*

class MainActivity : AppCompatActivity() {
    companion object {
        private var currentFragment: Int = -1
        private const val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1//System.identityHashCode(this).compareTo(0xFFFF)
        private const val GOOGLE_PROFILE_PERMISSION_REQUEST_CODE = 2//
        private const val DANGEROUS_PERMISSION_REQUEST_CODE = 3//
        private const val LOG_TAG: String = "CheckMainActivity"
    }
    private lateinit var viewModel: MyViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var fitnessOptions: FitnessOptions

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        when(item.itemId) {
            R.id.navigation_home     -> {
                if (currentFragment != 1) {
                    transaction.replace(R.id.fragment, HomeFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    currentFragment = 1
                    transaction.commit()
                }
            }
            R.id.navigation_workout  -> {
                if (currentFragment != 2) {
                    transaction.replace(R.id.fragment, WorkoutFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    currentFragment = 2
                    transaction.commit()
                }
            }
            R.id.navigation_about_me -> {
                if (currentFragment != 3) {
                    transaction.replace(R.id.fragment, PersonFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    currentFragment = 3
                    transaction.commit()
                }
            }
        }
        return@OnNavigationItemSelectedListener true
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataProcessing.loadData(DataProcessing.getSharedPreferences(this))

        supportFragmentManager.beginTransaction().add(R.id.fragment, HomeFragment()).commit()

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        fitnessInit()
        initGoogleProfilePermission()
        GoogleAccount.getPersonActivity(this)
        GoogleAccount.getPersonInfo()
        Log.d(LOG_TAG, Singleton.person.gender.value?.value)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Activity.RESULT_OK) {
            if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
                viewModel.accessGoogleFit()
            }
            if (requestCode == GOOGLE_PROFILE_PERMISSION_REQUEST_CODE) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onDestroy() {
        DataProcessing.saveData(Singleton.person)
        super.onDestroy()
    }

    fun changeGenderDialog(view: View) {
        MaterialDialog(this).show {
            listItemsSingleChoice(R.array.genders) { dialog, index, text ->
                when(index)
                {
                    0 -> Singleton.person.gender.value = Gender.MEN
                    1 -> Singleton.person.gender.value = Gender.WOMEN
                    2 -> Singleton.person.gender.value = Gender.OTHER
                }
            }
            when(Singleton.person.gender.value)
            {
                Gender.MEN -> toggleItemChecked(0)
                Gender.WOMEN -> toggleItemChecked(1)
                Gender.OTHER -> toggleItemChecked(2)
            }
            cornerRadius(16f)
            title(R.string.yourSex)
            negativeButton(R.string.cancel)
            positiveButton(R.string.mContinue)
            with(window) {
                this?.setGravity(Gravity.BOTTOM)
                val layoutParams = window?.attributes
                layoutParams?.y = 100
                this?.attributes = layoutParams
            }
        }
    }
    fun changeWeightDialog(view: View) = createMaterialDialog(this, "weight")
    fun changeHeightDialog(view: View) = createMaterialDialog(this, "height")

    private fun initGoogleProfilePermission() {
        if (!GoogleSignIn.hasPermissions(GoogleAccount.getLastSignInAccount(this),
                        Scope(Scopes.PROFILE))) {
            GoogleSignIn.requestPermissions(this,
                GOOGLE_PROFILE_PERMISSION_REQUEST_CODE,
                GoogleSignIn.getLastSignedInAccount(this),
                Scope(Scopes.PROFILE))
        }
        if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            val strings = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this, strings, DANGEROUS_PERMISSION_REQUEST_CODE)
        }
    }
    private fun fitnessInit() {
        fitnessOptions = com.example.testcoursework.utils.mFitness.FitnessBuilder.fitnessInit()
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
            requestPermission(fitnessOptions, GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
        } else {
            viewModel.accessGoogleFit()
        }
    }
    private fun requestPermission(fitnessOptions: FitnessOptions, requestCode: Int) {
        GoogleSignIn.requestPermissions(
            this,
            requestCode,
            GoogleSignIn.getLastSignedInAccount(this),
            fitnessOptions
        )
    }
    private fun createMaterialDialog(context: Context, param: String) {
        MaterialDialog(context).show {
            cornerRadius(16f)
            negativeButton(R.string.cancel)
            customView(R.layout.number_picker_dialog)
            with(window) {
                this?.setGravity(Gravity.BOTTOM)
                val layoutParams = window?.attributes
                layoutParams?.y = 100
                this?.attributes = layoutParams
            }
            with(numberPicker) {
                maxValue = 250
                when(param) {
                    "height" -> minValue = 70
                    "weight" -> minValue = 30
                }
                wrapSelectorWheel = false
            }
            when (param) {
                "height" -> {
                    title(R.string.yourHeight)
                    positiveButton(R.string.mContinue) { Singleton.person.height.value = numberPicker.value }
                    textView.text = context.getString(R.string.heightStringValue)
                    numberPicker.value = Singleton.person.height.value!!
                }
                "weight" -> {
                    title(R.string.yourWeight)
                    positiveButton(R.string.mContinue) { Singleton.person.weight.value = numberPicker.value }
                    textView.text = context.getString(R.string.kilogramStringRes)
                    numberPicker.value = Singleton.person.weight.value!!
                }
            }
        }
    }
}
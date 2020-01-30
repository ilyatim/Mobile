package com.example.testcoursework.viewModel

import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.testcoursework.data.repo.googleAccount.GoogleAccount
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType

class SplashViewModel(val app: Application) : AndroidViewModel(app) {
    companion object {
        private const val LOG_TAG: String = "ViewModel"
    }

    private val GOOGLE_SIGN_IN_ACCOUNT: GoogleSignInAccount? =
        GoogleAccount.getLastSignInAccount(app.baseContext)
    private val fitnessOptions: FitnessOptions =
        com.example.testcoursework.utils.mFitness.FitnessBuilder.fitnessInit()

    fun accessGoogleFit() {
        subscribeStepCount()
        subscribeCalories()
        subscribeDistance()
    }
    fun checkAllPermissions(): Boolean {
        return  checkProfilePermission() &&
                checkFitnessPermission() &&
                !checkLocationPermission()
    }

    private fun checkProfilePermission(): Boolean {
        return GoogleSignIn.hasPermissions(
            GoogleAccount.getLastSignInAccount(app),
            Scope(Scopes.PROFILE)
        )
    }
    fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(app,
            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
    }
    private fun checkFitnessPermission(): Boolean {
        return GoogleSignIn.hasPermissions(
            GoogleSignIn.getLastSignedInAccount(app),
            fitnessOptions)
    }
    private fun subscribeCalories() {
        GOOGLE_SIGN_IN_ACCOUNT?.let { it ->
            Fitness.getRecordingClient(app.baseContext, it)
                .subscribe(DataType.TYPE_CALORIES_EXPENDED)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Log.d(LOG_TAG, "SUCCESSFULLY - calories")
                    } else {
                        Log.d(LOG_TAG, "THERE A PROBLEM iN STEP COUNT - ${it.exception}")
                    }
                }
        }
    }
    private fun subscribeStepCount() {
        GOOGLE_SIGN_IN_ACCOUNT?.let { it ->
            Fitness.getRecordingClient(app.baseContext, it)
                .subscribe(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Log.d(LOG_TAG, "SUCCESSFULLY - step count")
                    } else {
                        Log.d(LOG_TAG, "THERE A PROBLEM iN STEP COUNT - ${it.exception}")
                    }
                }
        }
    }
    private fun subscribeDistance() {
        GOOGLE_SIGN_IN_ACCOUNT?.let { it ->
            Fitness.getRecordingClient(app.baseContext, it)
                .subscribe(DataType.TYPE_DISTANCE_DELTA)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Log.d(LOG_TAG, "SUCCESSFULLY - distance")
                    } else {
                        Log.d(LOG_TAG, "THERE A PROBLEM IN DISTANCE - ${it.exception}")
                    }
                }
        }
    }
}
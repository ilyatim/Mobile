package com.example.testcoursework.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import com.example.testcoursework.utils.googleAccount.GoogleAccount
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType

class MyViewModel(val app: Application) : AndroidViewModel(app) {
    companion object {
        private const val LOG_TAG: String = "ViewModel"
    }
    private val GOOGLE_SIGN_IN_ACCOUNT: GoogleSignInAccount? =
            GoogleAccount.getLastSignInAccount(app.baseContext)

    fun accessGoogleFit() {
        subscribeStepCount()
        subscribeCalories()
        subscribeDistance()
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
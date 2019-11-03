package com.example.testcoursework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import com.example.testcoursework.ui.activity.MainActivity
import com.example.testcoursework.utils.googleAccount.GoogleAccount
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType

class MyViewModel(val app: Application) : AndroidViewModel(app)
{
    companion object {
        private const val LOG_TAG: String = "ViewModel"
    }

    private var googleSignInAccount: GoogleSignInAccount? = null

    init {
        googleSignInAccount = GoogleAccount.getLastSignInAccount(app.baseContext)
    }

    fun accessGoogleFit()
    {
        googleSignInAccount?.let { it ->
            Fitness.getRecordingClient(app.baseContext, it)
                .subscribe(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Log.d(LOG_TAG, "SUCCESSFULLY")
                    }
                    else
                    {
                        Log.d(LOG_TAG, "THERE A PROBLEM - ${it.exception}")
                    }
                }
        }
    }
}
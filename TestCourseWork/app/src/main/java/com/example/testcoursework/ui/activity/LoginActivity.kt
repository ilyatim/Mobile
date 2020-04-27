package com.example.testcoursework.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityLoginBinding
import com.example.testcoursework.viewModel.SplashViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.fitness.FitnessOptions

class LoginActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    companion object {
        private const val PERMISSION_GRANTED = 0
        private const val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1//
        private const val GOOGLE_PROFILE_PERMISSION_REQUEST_CODE = 2//
        private const val LOCATION_PERMISSION_REQUEST_CODE = 3//
        private const val LOG_TAG: String = "CheckMainActivity"
    }

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: SplashViewModel
    private var fitnessOptions: FitnessOptions =
        com.example.testcoursework.utils.mFitness.FitnessBuilder.fitnessInit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window?.apply {
            decorView.systemUiVisibility = 0
            statusBarColor = ContextCompat.getColor(context,
                R.color.top_bar_background
            )
        }
        viewModel = ViewModelProvider.
            AndroidViewModelFactory.
            getInstance(this.application).
            create(SplashViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        Log.d(LOG_TAG, "$requestCode ${Activity.RESULT_OK} $resultCode")
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE    -> {
                    viewModel.accessGoogleFit()
                    profileInit()
                }
                GOOGLE_PROFILE_PERMISSION_REQUEST_CODE -> {
                    if(viewModel.checkLocationPermission()) locationInit()
                    else {
                        startActivity(Intent(this, MainActivity::class.java))
                        this.finish()
                    }
                }
            }
        } else Toast.makeText(this, "Ой, что-то пошло не так", Toast.LENGTH_LONG).show()
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults[0] == PERMISSION_GRANTED) {
            when(requestCode) {
                LOCATION_PERMISSION_REQUEST_CODE -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    this.finish()
                }
            }
        } else Toast.makeText(this, "Требуется разрешить доступ", Toast.LENGTH_LONG).show()
        super.onRequestPermissionsResult(requestCode,
            permissions,
            grantResults)
    }

    fun onClick(view: View) {
        fitnessInit()
    }

    private fun profileInit() {
        requestProfilePermission(Scope(Scopes.PROFILE), GOOGLE_PROFILE_PERMISSION_REQUEST_CODE)
    }
    private fun locationInit() {
        val strings = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, strings, LOCATION_PERMISSION_REQUEST_CODE)
    }
    private fun fitnessInit() {
        requestFitPermission(fitnessOptions, GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
    }
    private fun requestProfilePermission(scope: Scope, requestCode: Int) {
        GoogleSignIn.requestPermissions(
            this,
            requestCode,
            GoogleSignIn.getLastSignedInAccount(this),
            scope
        )
    }
    private fun requestFitPermission(fitnessOptions: FitnessOptions, requestCode: Int) {
        GoogleSignIn.requestPermissions(
            this,
            requestCode,
            GoogleSignIn.getLastSignedInAccount(this),
            fitnessOptions
        )
    }
}

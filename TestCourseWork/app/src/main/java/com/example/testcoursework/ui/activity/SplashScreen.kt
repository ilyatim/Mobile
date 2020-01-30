package com.example.testcoursework.ui.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.testcoursework.R
import com.example.testcoursework.data.repo.googleAccount.GoogleAccount
import com.example.testcoursework.databinding.ActivitySplashScreenBinding
import com.example.testcoursework.viewModel.SplashViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.fitness.FitnessOptions

class SplashScreen : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.
            AndroidViewModelFactory.
            getInstance(this.application).
            create(SplashViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        if (checkAllPermissions()) {
            viewModel.accessGoogleFit()
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
        else {
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }
    }
    private fun checkAllPermissions(): Boolean {
        return viewModel.checkAllPermissions()
    }
}

package com.example.testcoursework.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import com.example.testcoursework.R
import com.example.testcoursework.ui.dialog.ChangeGenderDialog

class ProfileViewModel(val app: Application) : AndroidViewModel(app)
{
    companion object {
        const val LOG_TAG: String = "ProfileViewModel"
    }
    lateinit var supportFragmentManager: FragmentManager
    val context: Context = app.baseContext
    val gender = ObservableField<String>("Non gender")
    val weight = ObservableFloat(0f)
    val height = ObservableInt(0)

    private fun changeToMen()
    {
        gender.set(R.string.men.toString())
    }
    private fun changeToWomen()
    {
        gender.set(R.string.women.toString())
    }
    fun changeGender()
    {
        Log.d(LOG_TAG, "Click")
        val dialog = ChangeGenderDialog()
        dialog.show(supportFragmentManager, "ChangeGender")
    }
}
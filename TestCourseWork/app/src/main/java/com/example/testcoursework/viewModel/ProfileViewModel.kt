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
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.example.testcoursework.R
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.ui.activity.ProfileActivity

class ProfileViewModel(val app: Application) : AndroidViewModel(app)
{
    companion object {
        const val LOG_TAG: String = "ProfileViewModel"
    }
    val context: Context = app.baseContext
    val gender = ObservableField<String>(Singleton.personActivity.person.value?.gender?.value)
    val weight = ObservableInt(Singleton.personActivity.person.value?.weight?.value!!)
    val height = ObservableInt(Singleton.personActivity.person.value?.height?.value!!)
}
package com.example.testcoursework.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.checkItem
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.afollestad.materialdialogs.list.toggleItemChecked
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityProfileBinding
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.viewModel.ProfileViewModel
import kotlinx.android.synthetic.main.number_picker_dialog.*

class ProfileActivity : AppCompatActivity()
{
    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        //observe()
    }
    /*private fun observe()
    {
        Singleton.person.weight.observe(this, Observer<Int> {
            viewModel.weight.set(it)
        })
        Singleton.person.height.observe(this, Observer<Int> {
            viewModel.height.set(it)
        })
        Singleton.person.gender.observe(this, Observer {
            viewModel.gender.set(it.name)
        })
    }
    fun changeGenderDialog(view: View)
    {
        MaterialDialog(this).show {
            listItemsSingleChoice(R.array.genders) { dialog, index, text ->
                Singleton.person.gender.value = text.toString()
            }
            when(viewModel.gender.get())
            {
                context.getString(R.string.men) -> toggleItemChecked(0)
                context.getString(R.string.women) -> toggleItemChecked(1)
                context.getString(R.string.anyGender) -> toggleItemChecked(2)
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

    private fun createMaterialDialog(context: Context, param: String)
    {
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
                maxValue = 300
                minValue = 10
                wrapSelectorWheel = false
            }
            when(param)
            {
                "height" -> {
                    title(R.string.yourHeight)
                    positiveButton(R.string.mContinue) { Singleton.person.height.value = numberPicker.value }
                    textView.text = context.getString(R.string.heightStringValue)
                    numberPicker.value = viewModel.height.get()
                }
                "weight" -> {
                    title(R.string.yourWeight)
                    positiveButton(R.string.mContinue) { Singleton.person.weight.value = numberPicker.value }
                    textView.text = context.getString(R.string.kilogramStringRes)
                    numberPicker.value = viewModel.weight.get()
                }
            }
        }
    }*/
}

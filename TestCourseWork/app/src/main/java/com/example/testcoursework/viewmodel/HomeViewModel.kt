package com.example.testcoursework.viewModel

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.testcoursework.R
import com.example.testcoursework.model.data.Singleton
import kotlinx.android.synthetic.main.calories_layout.view.*

class HomeViewModel: ViewModel() {
    companion object {
        private const val LOG_TAG: String = "ViewModel"
    }
    val countOfDrunkWater = ObservableInt(Singleton.personActivity.countOfDrunkWater.value!!)
    val coveredDistance = ObservableFloat(Singleton.personActivity.coveredDistance.value!!)
    val numberOfSteps = ObservableField(Singleton.personActivity.numberOfSteps.value!!)
    val calories = ObservableField(Singleton.personActivity.calories.value!!)
    val currentWeight = ObservableInt(Singleton.person.weight.value!!)
    val name = ObservableField(Singleton.person.name.value)
    val photoUrl = ObservableField(Singleton.person.photoUrl.value)

    fun increaseTheAmountOfWaterDrunk() {
        Singleton.personActivity.increaseWater()
    }
    fun animateTextView(textView: TextView,
                        initialValue: Int,
                        finalValue: Int
    ) {
        val valueAnimator: ValueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
        valueAnimator.duration = 1000
        valueAnimator.addUpdateListener {
            textView.text = valueAnimator.animatedValue.toString()
        }
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }
            override fun onAnimationCancel(animation: Animator?) {

            }
            override fun onAnimationRepeat(animation: Animator?) {

            }
            override fun onAnimationEnd(animation: Animator?) {
                when (textView.id) {
                    R.id.steps -> numberOfSteps.set(finalValue)
                    R.id.calories -> calories.set(finalValue)
                    R.id.weight -> currentWeight.set(finalValue)
                }
            }
        })
        valueAnimator.start()
    }
    fun animateTextView(textView: TextView,
                        initialValue: Float,
                        finalValue: Float
    ) {
        val valueAnimator: ValueAnimator = ValueAnimator.ofFloat(initialValue, finalValue)
        valueAnimator.duration = 1000
        valueAnimator.addUpdateListener {
            textView.text = valueAnimator.animatedValue.toString()
        }
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }
            override fun onAnimationCancel(animation: Animator?) {

            }
            override fun onAnimationRepeat(animation: Animator?) {

            }
            override fun onAnimationEnd(animation: Animator?) {
                when (textView.id) {
                    R.id.distance -> coveredDistance.set(finalValue)
                }
            }
        })
        valueAnimator.start()
    }
    fun animateProgressBar(progressBar: ProgressBar,
                           initialValue: Int,
                           finalValue: Int,
                           propertyName: String = "progress"
    ) {
        val objectAnimator = ObjectAnimator.ofInt(progressBar,
                propertyName,
                initialValue,
                finalValue)
        objectAnimator.duration = 1000
        objectAnimator.start()
    }
}

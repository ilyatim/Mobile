package com.example.testlivedata.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testlivedata.R
import com.example.testlivedata.data.dataController.DataController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener
{
    val liveData: LiveData<String>? = DataController.getInstance()?.getData()
    override fun onClick(v: View?)
    {
        liveData.
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        liveData?.observe(this, Observer<String> {
            textView.text = it
        })

    }

}

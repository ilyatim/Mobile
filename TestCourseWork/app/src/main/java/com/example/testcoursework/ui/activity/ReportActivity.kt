package com.example.testcoursework.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testcoursework.R
import com.example.testcoursework.databinding.ActivityReportBinding
import com.example.testcoursework.data.model.classes.Date
import com.example.testcoursework.viewModel.ReportActivityViewModel

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private lateinit var viewModel: ReportActivityViewModel
    private val onDateChangeListener = CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
        viewModel.getReport(
            Date(
                year,
                month,
                dayOfMonth
            )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_report)
        viewModel = ViewModelProviders.of(this).get(ReportActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.calendarView.setOnDateChangeListener(onDateChangeListener)
        binding.executePendingBindings()
    }
}

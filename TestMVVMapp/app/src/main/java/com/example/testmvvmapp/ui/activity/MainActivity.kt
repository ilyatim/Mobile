package com.example.testmvvmapp.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.testmvvmapp.viewmodel.MyViewModel
import com.example.testmvvmapp.R
import com.example.testmvvmapp.adapter.repositoryRecyclerViewAdapter.RepositoryRecyclerViewAdapter
import com.example.testmvvmapp.data.repository.Repository
import com.example.testmvvmapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener
{


    private lateinit var binding: ActivityMainBinding
    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        viewModel.repositories.observe(this, Observer<ArrayList<Repository>> { it?.let { repositoryRecyclerViewAdapter.replaceData(it)} })
    }
    override fun onItemClick(position: Int)
    {
        Log.d("MainActivity", "click")
    }
}

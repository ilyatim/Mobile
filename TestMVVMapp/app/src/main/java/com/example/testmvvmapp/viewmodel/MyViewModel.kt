package com.example.testmvvmapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.example.testmvvmapp.model.OnDataReadyCallBack
import com.example.testmvvmapp.model.OnRepositoryReadyCallback
import com.example.testmvvmapp.model.RepoModel
import com.example.testmvvmapp.data.repository.Repository

class MyViewModel(app: Application) : AndroidViewModel(app)
{

    var repoModel: RepoModel = RepoModel(getApplication())

    val text = ObservableField<String>("old data")

    val isLoading = ObservableField<Boolean>(false)

    var repositories = MutableLiveData<ArrayList<Repository>>()

    fun click()
    {
        loadRepositories()
        refresh()
    }
    fun loadRepositories()
    {
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>)
            {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }


    fun refresh()
    {
        isLoading.set(true)
        Log.d("viewModel", "refreshViewModel")
        repoModel.refreshData(object : OnDataReadyCallBack {
            override fun onDataReady(data: String)
            {
                Log.d("viewModel", "refreshOnDataReady")
                isLoading.set(false)
                text.set(data)
            }
        })
    }

}
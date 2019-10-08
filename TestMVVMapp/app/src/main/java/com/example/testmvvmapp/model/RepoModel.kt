package com.example.testmvvmapp.model

import android.content.Context
import android.os.Handler
import android.util.Log
import com.example.testmvvmapp.data.repository.Repository
import com.example.testmvvmapp.model.network.NetManager

class RepoModel(context: Context)
{

    val netManager = NetManager(context)

    fun refreshData(onDataReadyCallBack: OnDataReadyCallBack)
    {
        Log.d("viewModel", "refreshDataRepoModel")
        Handler().postDelayed({onDataReadyCallBack.onDataReady("new data")}, 2000)
    }
    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback)
    {
        var arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First", "Owner 1", 100, false))
        arrayList.add(Repository("Second", "Owner 2", 30, true))
        arrayList.add(Repository("Third", "Owner 3", 430, false))
        arrayList.add(Repository("Fourth", "Owner 4", 55, false))
        arrayList.add(Repository("Fifth", "Owner 5", 123, true))
        arrayList.add(Repository("Sixth", "Owner 6", 423, false))
        Handler().postDelayed({ onRepositoryReadyCallback.onDataReady(arrayList) },2000)
    }
}
interface OnDataReadyCallBack
{
    fun onDataReady(data: String)
}
interface OnRepositoryReadyCallback
{
    fun onDataReady(data: ArrayList<Repository>)
}
package com.example.testlivedata.data.dataController

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class DataController private constructor(): LiveData<String>()
{
    private val liveData: MutableLiveData<String> = MutableLiveData()
    fun getData(): LiveData<String> = liveData

    companion object{
        private var instance: DataController? = null
        fun getInstance(): DataController?
        {
            if(instance == null)
            {
                instance = DataController()
            }
            return instance
        }
    }
}

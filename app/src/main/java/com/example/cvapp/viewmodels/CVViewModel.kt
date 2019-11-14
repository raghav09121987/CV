package com.example.cvapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.cvapp.models.CVResponse
import com.example.cvapp.repository.CVRepo
import com.example.cvapp.repository.CVRepoImpl
import com.example.cvapp.util.Resource
import javax.inject.Inject

class CVViewModel @Inject constructor(private val repository: CVRepo): ViewModel() {

   var data: MediatorLiveData<Resource<CVResponse>> = MediatorLiveData()

    fun response(): LiveData<Resource<CVResponse>> {
        return data
    }
    fun getCV(){
        data.value = Resource.Loading()
        var source = LiveDataReactiveStreams.fromPublisher(repository.getCVData())
        with(data) {
            addSource(source) {
                    res -> data.value = res as Resource<CVResponse>?
            }
        }
    }
}
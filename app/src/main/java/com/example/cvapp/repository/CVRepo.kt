package com.example.cvapp.repository

import com.example.cvapp.models.CVResponse
import com.example.cvapp.util.Resource
import io.reactivex.Flowable

interface CVRepo {
    open fun getCVData() : Flowable<Resource<out CVResponse>>
}
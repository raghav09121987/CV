package com.example.cvapp.repository

import com.example.cvapp.models.CVResponse
import com.example.cvapp.network.CVApi
import com.example.cvapp.util.Resource
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CVRepoImpl @Inject constructor(private var cvApi: CVApi) : CVRepo {
    override fun getCVData() : Flowable<Resource<out CVResponse>> {
        return cvApi.getCVData()
            .onErrorReturn { CVResponse(null, null,null,null,null) }
            .map { cvResponse ->
                if(cvResponse.email.equals(null)){
                    Resource.Error("Err!!", null)
                }else{
                    Resource.Success(cvResponse)
                }
            }
            .subscribeOn(Schedulers.io())

    }
}
package com.example.cvapp.network

import com.example.cvapp.models.CVResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface CVApi {
    @GET("cv.json")
    fun getCVData(): Flowable<CVResponse>
}
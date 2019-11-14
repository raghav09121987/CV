package com.example.cvapp.di.cv

import com.example.cvapp.network.CVApi
import com.example.cvapp.repository.CVRepo
import com.example.cvapp.repository.CVRepoImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CVModule {

    @Provides
    fun provideCVApi(retrofit: Retrofit): CVApi {
        return retrofit.create(CVApi::class.java)
    }

    @Provides
    fun provideCVRepository(cvApi: CVApi): CVRepo {
        return CVRepoImpl(cvApi)
    }
}
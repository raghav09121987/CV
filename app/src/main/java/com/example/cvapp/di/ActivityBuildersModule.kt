package com.example.cvapp.di

import com.example.cvapp.di.cv.CVModule
import com.example.cvapp.di.cv.CVViewModelsModule
import com.example.cvapp.ui.CVActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            CVViewModelsModule::class,
            CVModule::class
        ]
    )
    abstract fun contributeCVActivity(): CVActivity
}
package com.example.cvapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.cvapp.viewmodels.ViewModelsProvidersFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
   abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelsProvidersFactory):ViewModelProvider.Factory
}
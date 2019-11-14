package com.example.cvapp.di.cv

import androidx.lifecycle.ViewModel
import com.example.cvapp.di.ViewModelKey
import com.example.cvapp.viewmodels.CVViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CVViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(CVViewModel::class)
    protected abstract fun movieListViewModel(moviesListViewModel: CVViewModel): ViewModel

}
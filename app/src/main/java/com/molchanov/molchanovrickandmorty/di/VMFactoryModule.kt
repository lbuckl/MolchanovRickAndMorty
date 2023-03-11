package com.molchanov.molchanovrickandmorty.di

import androidx.lifecycle.ViewModelProvider
import com.molchanov.molchanovrickandmorty.ui.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class VMFactoryModule {

    @Singleton
    @Binds
    abstract fun getVMFactory(vmFactory: ViewModelFactory): ViewModelProvider.NewInstanceFactory
}
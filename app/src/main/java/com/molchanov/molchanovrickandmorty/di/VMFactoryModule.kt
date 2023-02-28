package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.ui.base.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class VMFactoryModule {

    @Singleton
    @Provides
    fun getVMFactory(): ViewModelFactory {
       return ViewModelFactory()
    }
}
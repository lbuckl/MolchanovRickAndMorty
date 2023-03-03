package com.molchanov.molchanovrickandmorty.di

import com.molchanov.repository.remote.IRepositoryRemote
import com.molchanov.repository.remote.RepositoryRemoteImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryRemoteModule {

    @Singleton
    @Provides
    fun getRepositoryRemote(): IRepositoryRemote{
        return RepositoryRemoteImpl()
    }
}
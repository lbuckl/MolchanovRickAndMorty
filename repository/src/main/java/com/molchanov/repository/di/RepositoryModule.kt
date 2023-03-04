package com.molchanov.repository.di

import com.molchanov.repository.remote.IRepositoryRemote
import com.molchanov.repository.remote.RepositoryRemoteImpl
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DTOtoDomainMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun rickAndMortyRequest() = RickAndMortyRequestIml()

    @Singleton
    @Provides
    fun dtoToDomainMapper() = DTOtoDomainMapper()


    @Provides
    fun getRepositoryRemote(
        request: RickAndMortyRequestIml,
        dtoToDomainMapper: DTOtoDomainMapper
    ): IRepositoryRemote{
        return RepositoryRemoteImpl(request, dtoToDomainMapper)
    }
}
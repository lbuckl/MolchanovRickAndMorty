package com.molchanov.repository.di

import com.molchanov.repository.IRepository
import com.molchanov.repository.remote.RepositoryRemoteImpl
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DTOtoDomainMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryRemoteModule {

    @Provides
    fun rickAndMortyRequest() = RickAndMortyRequestIml()

    @Singleton
    @Provides
    fun dtoToDomainMapper() = DTOtoDomainMapper()


    @Provides
    fun getRepositoryRemote(
        request: RickAndMortyRequestIml,
        dtoToDomainMapper: DTOtoDomainMapper
    ): IRepository {
        return RepositoryRemoteImpl(request, dtoToDomainMapper)
    }
}
package com.molchanov.repository.di

import com.molchanov.repository.IRepository
import com.molchanov.repository.remote.RepositoryRemoteImpl
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DTOtoDomainMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryRemoteModule {

    @Provides
    fun rickAndMortyRequest() = RickAndMortyRequestIml()

    @Provides
    fun mapper() = DTOtoDomainMapper()


    @Singleton
    @Provides
    @Named("Remote")
    fun getRepositoryRemote(
        request: RickAndMortyRequestIml,
        mapper: DTOtoDomainMapper
    ): IRepository {
        return RepositoryRemoteImpl(request, mapper)
    }
}
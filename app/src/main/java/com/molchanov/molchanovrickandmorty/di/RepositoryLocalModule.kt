package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.App
import com.molchanov.repository.IRepository
import com.molchanov.repository.local.RepositoryLocalImpl
import com.molchanov.repository.local.characters.CharactersDbExist
import com.molchanov.repository.utils.DaoDomainMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryLocalModule {

    @Provides
    fun charDbExist(app: App): CharactersDbExist{
        return CharactersDbExist(app.getContext())
    }

    @Provides
    fun mapper(): DaoDomainMapper = DaoDomainMapper()

    @Provides
    @Named("Local")
    fun repositoryLocalImpl(
        dbExist: CharactersDbExist,
        mapper: DaoDomainMapper
    ): IRepository{
        return RepositoryLocalImpl(dbExist,mapper)
    }
}
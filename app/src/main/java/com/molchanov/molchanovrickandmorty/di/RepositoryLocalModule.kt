package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.App
import com.molchanov.repository.IRepository
import com.molchanov.repository.local.RepositoryLocalImpl
import com.molchanov.repository.local.characters.CharactersDbExist
import dagger.Module
import dagger.Provides

@Module
class RepositoryLocalModule {

    @Provides
    fun charDbExist(app: App): CharactersDbExist{
        return CharactersDbExist(app.getContext())
    }

    @Provides
    fun repositoryLocalImpl(dbExist: CharactersDbExist): IRepository{
        return RepositoryLocalImpl(dbExist)
    }
}
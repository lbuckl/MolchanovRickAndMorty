package com.molchanov.molchanovrickandmorty.di

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.cases.IRemoteRequest
import com.molchanov.repository.remote.characters.CharacterRepoRemoteImpl
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DtoDomainMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Набор объектов для реализации запроса данных от API Rick and Morty
 */
@Module
class RepositoryRemoteModule {

    @Singleton
    @Provides
    @Named("CharacterRepoRemote")
    fun repositoryCharacterRemoteImpl(
        request: RickAndMortyRequestIml,
        mapper: DtoDomainMapper
    ): IRemoteRequest<Int, String, CharacterPage, Character> {
        return CharacterRepoRemoteImpl(request,mapper)
    }
}
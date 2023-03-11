package com.molchanov.molchanovrickandmorty.di

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.cases.IRemoteRequest
import com.molchanov.repository.remote.characters.CharacterRepoRemoteImpl
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DTOtoDomainMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Набор объектов для реализации запроса данных от API Rick and Morty
 */
@Module
class RepositoryRemoteModule {

    @Provides
    fun rickAndMortyRequest() = RickAndMortyRequestIml()

    @Provides
    fun mapper() = DTOtoDomainMapper()

    @Singleton
    @Provides
    @Named("Remote")
    fun repositoryCharacterRemoteImpl(
        request: RickAndMortyRequestIml,
        mapper: DTOtoDomainMapper
    ): IRemoteRequest<Int, String, CharacterPage, Character> {
        return CharacterRepoRemoteImpl(request,mapper)
    }
}
package com.molchanov.molchanovrickandmorty.di

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.molchanovrickandmorty.App
import com.molchanov.repository.cases.ILocalRequest
import com.molchanov.repository.local.characters.CharacterRepoLocalImpl
import com.molchanov.repository.local.characters.CharactersDbExist
import com.molchanov.repository.utils.DaoDomainMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Набор объектов для реализации запроса данных в/из БД Room
 */
@Module
class RepositoryLocalModule {

    @Provides
    fun charDbExist(app: App): CharactersDbExist{
        return CharactersDbExist(app.getContext())
    }

    @Singleton
    @Provides
    @Named("CharacterRepoLocal")
    fun repositoryCharacterLocalImpl(
        dbExist: CharactersDbExist,
        mapper: DaoDomainMapper
    ): ILocalRequest<Int, Int, CharacterPage, Character> {
        return CharacterRepoLocalImpl(dbExist,mapper)
    }
}
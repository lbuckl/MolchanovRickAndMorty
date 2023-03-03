package com.molchanov.repository.remote

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DTOtoDomainMapper
import io.reactivex.rxjava3.core.Single

class RepositoryRemoteImpl(
    //private val dtoToDomainMapper: DTOtoDomainMapper
    //private val rmRequestImpl: RickAndMortyRequestIml
): IRepositoryRemote {

    override fun getCharacters(page: Int): Single<CharacterPage> {
        return RickAndMortyRequestIml().getCharactersRetrofit().getCharacters(page.toString()).map { dto ->
            DTOtoDomainMapper().charactersDTOtoDomainPage(dto)
        }
    }

    override fun getCharacterInfo(url: String): Single<Character> {
        return RickAndMortyRequestIml().getCharactersRetrofit().getCharacterDetails(url).map { dto ->
            DTOtoDomainMapper().charactersDTOtoDomain(dto)
        }
    }

    override fun saveCharacters(characters: List<Character>) {
        //TODO nothing
    }
}
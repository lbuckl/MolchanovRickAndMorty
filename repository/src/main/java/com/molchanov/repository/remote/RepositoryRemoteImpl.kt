package com.molchanov.repository.remote

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DTOtoDomainMapper
import io.reactivex.rxjava3.core.Single

class RepositoryRemoteImpl(
    private val rmRequestImpl: RickAndMortyRequestIml,
    private val dtoToDomainMapper: DTOtoDomainMapper
    ): IRepositoryRemote {

    override fun getCharacters(page: Int): Single<CharacterPage> {
        return rmRequestImpl.getCharactersRetrofit().getCharacters(page.toString()).map { dto ->
            dtoToDomainMapper.charactersDTOtoDomainPage(dto)
        }
    }

    override fun getCharacterInfo(url: String): Single<Character> {
        return rmRequestImpl.getCharactersRetrofit().getCharacterDetails(url).map { dto ->
            dtoToDomainMapper.charactersDTOtoDomain(dto)
        }
    }

    override fun saveCharacters(characters: List<Character>) {
        //TODO nothing
    }
}
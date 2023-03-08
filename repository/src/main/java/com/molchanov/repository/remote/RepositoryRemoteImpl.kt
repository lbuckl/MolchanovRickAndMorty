package com.molchanov.repository.remote

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.IRepository
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DTOtoDomainMapper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryRemoteImpl(
    private val rmRequestImpl: RickAndMortyRequestIml,
    private val dtoToDomainMapper: DTOtoDomainMapper
    ): IRepository {

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

    override fun saveCharacters(characterPage: CharacterPage, page: Int) {
        //TODO nothing
    }
}
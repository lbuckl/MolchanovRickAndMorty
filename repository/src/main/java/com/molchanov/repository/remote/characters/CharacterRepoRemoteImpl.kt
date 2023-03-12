package com.molchanov.repository.remote.characters

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.cases.IRemoteRequest
import com.molchanov.repository.remote.retrofit.RickAndMortyRequestIml
import com.molchanov.repository.utils.DtoDomainMapper
import io.reactivex.rxjava3.core.Single

/**
 * Класс реализующий взаимодействие с API персонажей Rick and Morty
 */
class CharacterRepoRemoteImpl(
    private val rmRequestImpl: RickAndMortyRequestIml,
    private val dtoDomainMapper: DtoDomainMapper
    ): IRemoteRequest<Int, String, CharacterPage, Character> {

    override fun getData(requestData: Int): Single<CharacterPage> {
        return rmRequestImpl.getCharactersRetrofit().getCharacters(requestData.toString()).map { dto ->
            dtoDomainMapper.charactersDTOtoDomainPage(dto, requestData)
        }
    }

    override fun getDetailInfo(id: String): Single<List<Character>> {
        TODO()
    }
}
package com.molchanov.repository.cases

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import io.reactivex.rxjava3.core.Single

interface ICharacterRequest {

    //fun getCharacters(): Single<List<Character>>

    fun getCharacters(page: Int): Single<CharacterPage>

    fun getCharacterInfo(url: String): Single<Character>

    fun saveCharacters(characterPage: CharacterPage, page: Int)
}
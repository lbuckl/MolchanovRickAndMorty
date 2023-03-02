package com.molchanov.repository.cases

import com.molchanov.domain.character.*
import io.reactivex.rxjava3.core.Single

interface ICharacterRequest {

    fun getCharacters(): Single<List<Character>>

    fun getCharacterInfo(url: String): Single<Character>

    fun saveCharacters(characters: List<Character>)
}
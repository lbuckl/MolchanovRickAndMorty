package com.molchanov.repository.local

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.IRepository
import com.molchanov.repository.local.characters.CharactersDbExist
import io.reactivex.rxjava3.core.Single

class RepositoryLocalImpl(private val dbExist: CharactersDbExist): IRepository {


    override fun getCharacters(page: Int): Single<CharacterPage> {
        TODO("Not yet implemented")
    }

    override fun getCharacterInfo(url: String): Single<Character> {
        TODO("Not yet implemented")
    }

    override fun saveCharacters(characters: List<Character>) {
        TODO("Not yet implemented")
    }
}
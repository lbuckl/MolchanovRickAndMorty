package com.molchanov.repository.local

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.IRepository
import com.molchanov.repository.local.characters.CharactersDbExist
import com.molchanov.repository.utils.DaoDomainMapper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryLocalImpl(
    private val dbExist: CharactersDbExist,
    private val mapper: DaoDomainMapper
    ): IRepository {

    override fun getCharacters(page: Int): Single<CharacterPage> {
        return dbExist.getCharactersDB().getDAO().queryPage(page).map {
            mapper.daoToDomain(it)
        }
    }

    override fun getCharacterInfo(url: String): Single<Character> {
        TODO("Not yet implemented")
    }

    override fun saveCharacters(characterPage: CharacterPage, page: Int) {
        dbExist.getCharactersDB().getDAO().insertAll(mapper.domainToDao(characterPage, page))
    }
}
package com.molchanov.repository.local.characters

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.cases.ILocalRequest
import com.molchanov.repository.utils.DaoDomainMapper
import io.reactivex.rxjava3.core.Single

/**
 * Класс реализующий взаимодействие с БД персонажей
 */
class CharacterRepoLocalImpl(
    private val dbExist: CharactersDbExist,
    private val mapper: DaoDomainMapper
    ): ILocalRequest<Int, Int, CharacterPage, Character> {

    private var lastPage: CharacterPage? = null

    override fun getData(requestData: Int): Single<CharacterPage> {

        return dbExist.getCharactersDB().getDAO().queryPage(requestData).map {
            mapper.daoToDomain(it)
        }
    }

    override fun getDetailInfo(id: Int): Character? {
        lastPage?.let {
                it.characterList.forEach { char ->
                    if (char.id == id) return char
                }

        }

        return null
    }

    override fun saveData(data: CharacterPage, key: Int) {

        dbExist.getCharactersDB().getDAO().insertAll(mapper.domainToDao(data, key))

        lastPage = data
    }
}
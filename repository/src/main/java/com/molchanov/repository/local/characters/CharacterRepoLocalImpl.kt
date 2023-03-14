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

        return dbExist.getCharacterEpisodeDB().getDAO().queryPageAndEpisodes(requestData).map { data ->
            mapper.daoCharacterAndEpisodesToDomain(data)
        }
    }

    override fun saveData(data: CharacterPage, key: Int) {

        data.characterList.forEach { char ->

            dbExist.getCharacterEpisodeDB().getDAO().insertCharacter(
                mapper.characterDomainToDao(char, data.pageActual)
            )

            dbExist.getCharacterEpisodeDB().getDAO().insertEpisodes(
                mapper.episodeDomainToEntity(char)
            )
        }

        lastPage = data
    }
}
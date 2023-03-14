package com.molchanov.repository.local.characters

import android.util.Log
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
    ): ILocalRequest<Int, Int, String, CharacterPage, Character> {

    private var lastPage: CharacterPage? = null

    override fun getData(requestData: Int): Single<CharacterPage> {

        return dbExist.getCharacterEpisodeDB().getDAO().queryPageAndEpisodes(requestData).map { data ->
            mapper.daoCharacterAndEpisodesToDomain(data)
        }
    }

    override fun getSearchedData(requestData: Int, searchWord: String): Single<CharacterPage> {

        return dbExist.getCharacterEpisodeDB().getDAO().queryPageAndEpisodes(requestData).map { data ->
            mapper.daoCharacterAndEpisodesToDomainSearch(data, searchWord)
        }
    }

    override fun saveData(data: CharacterPage, key: Int) {

        data.characterList.forEach { char ->

            dbExist.getCharacterEpisodeDB().getDAO().insertCharacter(
                mapper.characterDomainToDao(char, data.pageActual)
            )

            dbExist.getCharacterEpisodeDB().getDAO().insertEpisodes(
                mapper.episodeDomainToDao(char)
            )
        }

        lastPage = data
    }


}
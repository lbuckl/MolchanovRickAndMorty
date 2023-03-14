package com.molchanov.repository.utils

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.local.characters.entityes.CharacterEpisodeEntity
import com.molchanov.repository.local.characters.entityes.CharacterPageAndEpisodes
import com.molchanov.repository.local.characters.entityes.CharacterPageEntity
import javax.inject.Inject

class DaoDomainMapper @Inject constructor(){

    fun characterDomainToDao(character: Character, page: Int): CharacterPageEntity =
            CharacterPageEntity(
                character.id.toLong(),
                42,
                page,
                character.name,
                character.spec,
                character.status,
                character.gender,
                character.imgUrl
            )


    fun episodeDomainToDao(character: Character): List<CharacterEpisodeEntity> {

        val result: MutableList<CharacterEpisodeEntity> = mutableListOf()

        character.episodes.forEach {
            result.add(
                CharacterEpisodeEntity(
                    character.id.toLong(),
                    it
                )
            )
        }

        return result.toList()

    }

    fun daoCharacterAndEpisodesToDomain(characterPageAndEpisodes: List<CharacterPageAndEpisodes>): CharacterPage =
        CharacterPage(
            characterPageAndEpisodes[0].characterPage.pageNum,
            characterPageAndEpisodes[0].characterPage.pageActual,
            null,
            null,
            characterPageAndEpisodes.map {
                Character(
                    it.characterPage.id.toInt(),
                    it.characterPage.name,
                    it.characterPage.spec,
                    it.characterPage.status,
                    it.characterPage.gender,
                    it.characterPage.imgUrl,
                    it.characterEpisodeEntities.map { ep->
                        ep.episode
                    }
                )
            }
        )

    fun daoCharacterAndEpisodesToDomainSearch(
        characterPageAndEpisodes: List<CharacterPageAndEpisodes>,
        searchWord: String
    ): CharacterPage {

        val result = mutableListOf<CharacterPageAndEpisodes>()

        characterPageAndEpisodes.forEach {
            if (findWordInText(it.characterPage.name, searchWord)||
                findWordInText(it.characterPage.status, searchWord)||
                findWordInText(it.characterPage.gender, searchWord)||
                findWordInText(it.characterPage.spec, searchWord)
            ) result.add(it)
        }

        return CharacterPage(
            characterPageAndEpisodes[0].characterPage.pageNum,
            characterPageAndEpisodes[0].characterPage.pageActual,
            null,
            null,
            result.toList().map {
                Character(
                    it.characterPage.id.toInt(),
                    it.characterPage.name,
                    it.characterPage.spec,
                    it.characterPage.status,
                    it.characterPage.gender,
                    it.characterPage.imgUrl,
                    it.characterEpisodeEntities.map { ep->
                        ep.episode
                    }
                )
            }
        )
    }

}
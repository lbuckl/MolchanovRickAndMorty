package com.molchanov.repository.utils

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.local.characters.CharacterPageEntity
import javax.inject.Inject

class DaoDomainMapper @Inject constructor(){

    fun domainToDao(characterPage: CharacterPage, page: Int): List<CharacterPageEntity> =
        characterPage.characterList.map {
                CharacterPageEntity(
                    it.id.toLong(),
                    characterPage.pageNum,
                    page,
                    it.name,
                    it.spec,
                    it.status,
                    it.gender,
                    it.imgUrl
                )
            }


    fun daoToDomain(characterPage: List<CharacterPageEntity>): CharacterPage =
        CharacterPage(
            characterPage[0].pageNum,
            characterPage[0].pageActual,
            null,
            null,
            characterPage.map {
                Character(
                    it.id.toInt(),
                    it.name,
                    it.spec,
                    it.status,
                    it.gender,
                    it.imgUrl
                )
            }
        )
}
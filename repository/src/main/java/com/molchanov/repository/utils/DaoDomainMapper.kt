package com.molchanov.repository.utils

import android.util.Log
import com.molchanov.domain.character.*
import com.molchanov.repository.local.characters.CharacterPageEntity

class DaoDomainMapper {

    fun domainToDao(characterPage: CharacterPage, page: Int): List<CharacterPageEntity> {
        return characterPage.characterList.map {
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
    }

    fun daoToDomain(characterPage: List<CharacterPageEntity>): CharacterPage {
        Log.v("@@@","${characterPage.size}")
        return CharacterPage(
            characterPage[0].pageNum,
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
}
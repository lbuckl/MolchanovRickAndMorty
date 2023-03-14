package com.molchanov.repository.utils

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.remote.characters.dto.CharacterDetails
import com.molchanov.repository.remote.characters.dto.CharacterDto
import javax.inject.Inject

class DtoDomainMapper @Inject constructor(){

    fun charactersDTOtoDomainPage(characters: CharacterDto, page: Int): CharacterPage =
        CharacterPage(
            pageNum = characters.info.pages,
            pageActual = page,
            prev = characters.info.prev,
            next = characters.info.next,
            characters.results.map {
                charactersDTOtoDomain(it)
            }
        )

    private fun charactersDTOtoDomain(character: CharacterDetails): Character =
        Character(
            id = character.id,
            name = character.name,
            spec = character.species,
            status = character.status,
            gender = character.gender,
            imgUrl = character.image,
            episodes = character.episode.map {
                it
            }
        )

    fun charactersDTOtoDomainPageSearched(
        characters: CharacterDto,
        page: Int,
        searchWord: String
    ): CharacterPage {

        val result = mutableListOf<CharacterDetails>()

        characters.results.forEach {
            if (
                findWordInText(it.name, searchWord)||
                findWordInText(it.status, searchWord)||
                findWordInText(it.gender, searchWord)||
                findWordInText(it.species, searchWord)
            ) {
                result.add(it)
            }
        }

        return CharacterPage(
            pageNum = characters.info.pages,
            pageActual = page,
            prev = characters.info.prev,
            next = characters.info.next,
            result.toList().map {
                charactersDTOtoDomain(it)
            }
        )
    }
}
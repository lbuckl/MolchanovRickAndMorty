package com.molchanov.repository.utils

import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.repository.remote.dto.characters.CharacterDTO
import com.molchanov.repository.remote.dto.characters.CharacterDetails

class DTOtoDomainMapper {

    fun charactersDTOtoDomainPage(characters: CharacterDTO): CharacterPage =
        CharacterPage(
            pageNum = characters.info.pages,
            prev = characters.info.prev,
            next = characters.info.next,
            characters.results.map {
                charactersDTOtoDomain(it)
            }
        )

    fun charactersDTOtoDomain(character: CharacterDetails): Character =
        Character(
            id = character.id,
            name = character.name,
            spec = character.species,
            status = character.status,
            gender = character.gender,
            imgUrl = character.image
        )
}
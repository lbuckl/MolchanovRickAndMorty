package com.molchanov.repository.utils

import com.molchanov.domain.character.*
import com.molchanov.repository.remote.dto.characters.CharacterDTO
import com.molchanov.repository.remote.dto.characters.CharacterDetails

class DTOtoDomainMapper {

    fun charactersDTOtoDomainList(characters: CharacterDTO): List<Character> =
        characters.results.map {
            charactersDTOtoDomain(it)
        }

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
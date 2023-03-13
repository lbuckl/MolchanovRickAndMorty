package com.molchanov.repository.local.characters.entityes

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterPageAndEpisodes (

    @Embedded
    val characterPage: CharacterPageEntity,

    @Relation(parentColumn = "id", entity = CharacterEpisodeEntity::class, entityColumn = "characterId")
    val characterEpisodeEntities: List<CharacterEpisodeEntity>
    )
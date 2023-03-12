package com.molchanov.repository.local.characters

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterEpisodeTab")
data class CharacterEpisodeEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val CharacterId: Int,
    val episode: String
)
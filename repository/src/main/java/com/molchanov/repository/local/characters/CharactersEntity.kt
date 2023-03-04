package com.molchanov.repository.local.characters

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterPageTab")
data class CharactersEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val characterId: Int,
    val page: Int,
    val name: String
)
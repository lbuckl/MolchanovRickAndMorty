package com.molchanov.repository.local.characters.entityes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterPageTab")
data class CharacterPageEntity (
    @PrimaryKey
    val id: Long,
    val pageNum: Int,
    val pageActual: Int,
    val name: String,
    val spec: String, //Вид
    val status: String,
    val gender: String,
    val imgUrl: String
)

@Entity(
    tableName = "CharacterEpisodeTab",

    foreignKeys = [ForeignKey(
        entity = CharacterPageEntity::class,
        parentColumns = ["id"],
        childColumns = ["characterId"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["characterId", "episode"]
)
data class CharacterEpisodeEntity (
    val characterId: Long,
    val episode: String
)
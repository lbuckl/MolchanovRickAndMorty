package com.molchanov.repository.local.characters

import androidx.room.Entity
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
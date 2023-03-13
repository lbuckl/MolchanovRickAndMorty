package com.molchanov.repository.local.characters

import androidx.room.Database
import androidx.room.RoomDatabase
import com.molchanov.repository.local.characters.entityes.CharacterEpisodeEntity
import com.molchanov.repository.local.characters.entityes.CharacterPageEntity

/**
 * БД персонажей
 */
@Database(entities = [
    CharacterPageEntity::class,
    CharacterEpisodeEntity::class
    ], version = 1)

abstract class CharactersDB: RoomDatabase() {

    companion object {
        const val CHAR_DB_NAME = "CHARACTER_DB"
        const val CHAR_EPISODE_DB_NAME = "CHARACTER_EPISODE_DB"
    }

    abstract fun getDAO(): CharactersDAO
}
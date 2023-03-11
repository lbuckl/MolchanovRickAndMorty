package com.molchanov.repository.local.characters

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * БД персонажей
 */
@Database(entities = [CharacterPageEntity::class], version = 1)
abstract class CharactersDB: RoomDatabase() {
    companion object {
        const val CHAR_DB_NAME = "CHARACTER_DB"
    }

    abstract fun getDAO(): CharactersDAO
}
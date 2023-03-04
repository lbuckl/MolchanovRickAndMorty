package com.molchanov.repository.local.characters

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharactersEntity::class], version = 1)
abstract class CharactersDB: RoomDatabase() {
    companion object {
        const val CHAR_DB_NAME = "CHAR_DB"
    }

    abstract fun getDAO(): CharactersDAO
}
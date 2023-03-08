package com.molchanov.repository.local.characters


import android.content.Context
import android.util.Log
import androidx.room.Room

class CharactersDbExist(private val context: Context) {

    private val translateDB: CharactersDB? = null

    fun getCharactersDB(): CharactersDB {
        return translateDB
            ?: Room.databaseBuilder(
                context,
                CharactersDB::class.java,
                CharactersDB.CHAR_DB_NAME
            )
            .build()
    }
}

package com.molchanov.repository.local.characters


import android.content.Context
import androidx.room.Room

class CharactersDbExist(private val context: Context) {

    private val characterDB: CharactersDB? = null

    private val characterEpisodeDB: CharactersDB? = null

    fun getCharactersDB(): CharactersDB {
        return characterDB
            ?: Room.databaseBuilder(
                context,
                CharactersDB::class.java,
                CharactersDB.CHAR_DB_NAME
            ).build()
    }

    fun getCharacterEpisodeDB(): CharactersDB {
        return characterEpisodeDB
            ?: Room.databaseBuilder(
                context,
                CharactersDB::class.java,
                CharactersDB.CHAR_EPISODE_DB_NAME
            ).build()
    }
}

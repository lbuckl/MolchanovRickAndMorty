package com.molchanov.repository.local.characters


import android.content.Context
import androidx.room.Room

class CharactersDbExist(private val context: Context) {


    private val characterEpisodeDB: CharactersDB? = null

    fun getCharacterEpisodeDB(): CharactersDB {
        return characterEpisodeDB
            ?: Room.databaseBuilder(
                context,
                CharactersDB::class.java,
                CharactersDB.CHAR_EPISODE_DB_NAME
            ).build()
    }
}

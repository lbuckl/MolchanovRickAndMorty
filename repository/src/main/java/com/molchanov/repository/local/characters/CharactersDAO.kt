package com.molchanov.repository.local.characters

import androidx.room.*

@Dao
interface CharactersDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(characters: List<CharactersEntity>)

    @Query("SELECT * FROM CharacterPageTab WHERE page = :charPage")
    fun queryPage(charPage: Int): List<CharactersEntity>
}
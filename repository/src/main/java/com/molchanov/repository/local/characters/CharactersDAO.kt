package com.molchanov.repository.local.characters

import androidx.room.*
import io.reactivex.rxjava3.core.Single

@Dao
interface CharactersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterPageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisodes(episodes: List<CharacterEpisodeEntity>)

    @Query("SELECT * FROM CharacterPageTab WHERE pageActual = :charPage")
    fun queryPage(charPage: Int): Single<List<CharacterPageEntity>>
}
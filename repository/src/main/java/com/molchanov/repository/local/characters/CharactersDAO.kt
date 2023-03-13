package com.molchanov.repository.local.characters

import androidx.room.*
import com.molchanov.domain.character.*
import com.molchanov.repository.local.characters.entityes.CharacterEpisodeEntity
import com.molchanov.repository.local.characters.entityes.CharacterPageAndEpisodes
import com.molchanov.repository.local.characters.entityes.CharacterPageEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CharactersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterPageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisodes(episodes: List<CharacterEpisodeEntity>)

    @Query("SELECT * FROM CharacterPageTab WHERE pageActual = :charPage")
    fun queryPageAndEpisodes(charPage: Int): Single<List<CharacterPageAndEpisodes>>
}
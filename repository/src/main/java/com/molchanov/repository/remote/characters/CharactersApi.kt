package com.molchanov.repository.remote.characters

import com.molchanov.repository.remote.characters.*
import com.molchanov.repository.remote.characters.dto.CharacterDetails
import com.molchanov.repository.remote.characters.dto.CharacterDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Интерфейс для запроса из API персонажей Rick and Morty
 */
interface CharactersApi {

    @GET("api/character")
    fun getCharacters(@Query("page") page: String): Single<CharacterDto>

    @GET
    fun getCharacterDetails(@Url url: String): Single<CharacterDetails>
}
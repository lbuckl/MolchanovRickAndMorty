package com.molchanov.repository.remote.retrofit

import com.molchanov.repository.remote.dto.characters.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CharactersApi {

    @GET("api/character")
    fun getCharacters(@Query("page") page: String): Single<CharacterDTO>

    @GET
    fun getCharacterDetails(@Url url: String): Single<CharacterDetails>
}
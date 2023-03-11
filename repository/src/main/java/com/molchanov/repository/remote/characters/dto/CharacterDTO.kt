package com.molchanov.repository.remote.characters.dto

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterDetails>
)
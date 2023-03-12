package com.molchanov.repository.remote.characters.dto


import com.google.gson.annotations.SerializedName

data class CharacterDetails(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("episode")
    val episode: List<String>,
    //Возможно пригодятся
    @Transient
    @SerializedName("origin")
    val origin: Origin,
    @Transient
    @SerializedName("type")
    val type: String,
    @Transient
    @SerializedName("url")
    val url: String
)
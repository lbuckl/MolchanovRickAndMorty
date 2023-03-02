package com.molchanov.repository.remote.dto.characters


import com.google.gson.annotations.Expose
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
    //Возможно пригодятся
    @Transient
    @SerializedName("type")
    val type: String,
    @Transient
    @SerializedName("url")
    val url: String,
    @Transient
    @SerializedName("created")
    val created: String,
    @Transient
    @SerializedName("episode")
    val episode: List<String>
)
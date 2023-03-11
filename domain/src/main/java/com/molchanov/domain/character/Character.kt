package com.molchanov.domain.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character (
        val id: Int,
        val name: String,
        val spec: String, //Вид
        val status: String,
        val gender: String,
        val imgUrl: String
        ): Parcelable
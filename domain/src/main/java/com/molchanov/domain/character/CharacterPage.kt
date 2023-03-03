package com.molchanov.domain.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterPage (
    val pageNum: Int,
    val prev: String?,
    val next: String?,
    val characterList: List<Character>
    ): Parcelable
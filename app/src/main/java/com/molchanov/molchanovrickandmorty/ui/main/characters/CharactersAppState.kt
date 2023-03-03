package com.molchanov.molchanovrickandmorty.ui.main.characters

import com.molchanov.domain.character.*
import com.molchanov.molchanovrickandmorty.ui.base.AppState
import io.reactivex.rxjava3.core.Single

sealed class CharactersAppState: AppState() {
    data class Success(val data: List<Character>) : CharactersAppState()
    data class Error(val errorMsg: String) : CharactersAppState()
}
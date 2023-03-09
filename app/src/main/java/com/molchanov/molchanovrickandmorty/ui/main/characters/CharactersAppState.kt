package com.molchanov.molchanovrickandmorty.ui.main.characters

import com.molchanov.domain.character.CharacterPage
import com.molchanov.molchanovrickandmorty.ui.base.AppState

sealed class CharactersAppState: AppState() {
    data class Success(val data: CharacterPage) : CharactersAppState()
    data class Loading(val isLoading: Boolean): CharactersAppState()
    data class Error(val errorMsg: String) : CharactersAppState()
}
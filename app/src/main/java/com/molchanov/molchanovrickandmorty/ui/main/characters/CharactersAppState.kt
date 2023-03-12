package com.molchanov.molchanovrickandmorty.ui.main.characters

import com.molchanov.domain.character.*
import com.molchanov.molchanovrickandmorty.ui.base.AppState

sealed class CharactersAppState: AppState() {

    //Состояния страницы
    data class Loading(val isLoading: Boolean): CharactersAppState()
    data class Success(val data: CharacterPage) : CharactersAppState()
    data class Error(val errorMsg: String) : CharactersAppState()
    //Состояния детальной информации
    data class SuccessCharacter(val data: Character) : CharactersAppState()
    data class ErrorCharacter(val errorMsg: String) : CharactersAppState()


}
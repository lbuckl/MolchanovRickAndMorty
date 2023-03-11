package com.molchanov.molchanovrickandmorty.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molchanov.molchanovrickandmorty.ui.main.characters.CharactersViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(): ViewModelProvider.NewInstanceFactory() {

    private val viewModelMap = HashMap<Class<*>, ViewModel>()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CharactersViewModel::class.java -> {
                if (viewModelMap[modelClass] == null) {
                    val model = CharactersViewModel()
                    viewModelMap[modelClass] = model
                    model as T
                } else viewModelMap[modelClass] as T
            }
            else -> {
                if (viewModelMap[modelClass] == null) {
                    val model = SomeViewModel()
                    viewModelMap[modelClass] = model
                    model as T
                } else viewModelMap[modelClass] as T
            }
        }
    }
}
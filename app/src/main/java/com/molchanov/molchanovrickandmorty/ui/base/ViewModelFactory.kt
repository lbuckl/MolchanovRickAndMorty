package com.molchanov.molchanovrickandmorty.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory: ViewModelProvider.NewInstanceFactory() {

    //private var data: V? = null

    private val viewModelMap = HashMap<Class<*>, ViewModel>()

    /*fun setData(userData: V) {
        data = userData
    }*/

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            SomeViewModel::class.java -> {
                if (viewModelMap[modelClass] == null) {
                    val model = SomeViewModel()
                    viewModelMap[modelClass] = model
                    model as T
                } else viewModelMap[modelClass] as T
            }
            else -> super.create(modelClass)
        }
    }
}
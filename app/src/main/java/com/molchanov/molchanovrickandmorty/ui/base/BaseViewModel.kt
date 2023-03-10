package com.molchanov.molchanovrickandmorty.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V : AppState>(
    val liveData: MutableLiveData<V> = MutableLiveData<V>()
) : ViewModel()
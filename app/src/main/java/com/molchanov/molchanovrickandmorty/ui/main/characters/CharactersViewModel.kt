package com.molchanov.molchanovrickandmorty.ui.main.characters

import androidx.lifecycle.LiveData
import com.molchanov.molchanovrickandmorty.ui.base.BaseViewModel
import com.molchanov.repository.remote.IRepositoryRemote
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CharactersViewModel: BaseViewModel<CharactersAppState>() {

    @Inject lateinit var repoRemote: IRepositoryRemote

    fun getMyLiveData(): LiveData<CharactersAppState> {
        if (liveData.value == null) getData(1)
        return liveData
    }

    fun getData(page: Int){
        repoRemote.getCharacters(page)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    liveData.postValue(CharactersAppState.Success(it))
                },
                {
                    liveData.postValue(CharactersAppState.Error("Loading Error"))
                }
            )
    }
}
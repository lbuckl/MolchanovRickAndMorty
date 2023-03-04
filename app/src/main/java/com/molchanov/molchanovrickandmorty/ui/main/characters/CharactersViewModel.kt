package com.molchanov.molchanovrickandmorty.ui.main.characters

import androidx.lifecycle.LiveData
import com.molchanov.molchanovrickandmorty.ui.base.BaseViewModel
import com.molchanov.repository.remote.IRepositoryRemote
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class CharactersViewModel: BaseViewModel<CharactersAppState>() {

    @Inject lateinit var repoRemote: IRepositoryRemote

    @Inject @Named("io")lateinit var schedulerIO: Scheduler

    fun getMyLiveData(): LiveData<CharactersAppState> {
        if (liveData.value == null) getData(1)
        return liveData
    }

    fun getData(page: Int){
        repoRemote.getCharacters(page)
            .subscribeOn(schedulerIO)
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
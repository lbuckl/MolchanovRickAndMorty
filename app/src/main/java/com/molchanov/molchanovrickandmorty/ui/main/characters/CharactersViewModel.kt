package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.molchanov.molchanovrickandmorty.data.networkstatus.INetworkStatus
import com.molchanov.molchanovrickandmorty.ui.base.BaseViewModel
import com.molchanov.repository.IRepository
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

class CharactersViewModel: BaseViewModel<CharactersAppState>() {

    @Inject
    @Named("Remote")
    lateinit var repoRemote: IRepository

    @Inject
    @Named("Local")
    lateinit var repoLocal: IRepository

    @Inject @Named("io")
    lateinit var schedulerIO: Scheduler

    @Inject
    lateinit var networkStatus: INetworkStatus

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getMyLiveData(): LiveData<CharactersAppState> {
        if (liveData.value == null) getData(1)
        return liveData
    }

    @SuppressLint("CheckResult")
    fun getData(page: Int){
        networkStatus.isOnlineSingle()
            .subscribeWith(object : SingleObserver<Boolean>{

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    liveData.postValue(CharactersAppState.Error("Network"))
                }

                override fun onSuccess(t: Boolean) {
                    if (t) disposable.add(
                        repoRemote.getCharacters(page)
                            .subscribeOn(schedulerIO)
                            .subscribe(
                        {
                            liveData.postValue(CharactersAppState.Success(it))
                            repoLocal.saveCharacters(it, page)
                        },
                        {
                            liveData.postValue(CharactersAppState.Error("Loading"))
                        }
                        )
                    )
                    else disposable.add(
                        repoLocal.getCharacters(page)
                            .subscribeOn(schedulerIO)
                            .subscribe(
                        {
                            liveData.postValue(CharactersAppState.Success(it))
                        },
                        {
                            liveData.postValue(CharactersAppState.Error("Loading"))
                        }
                        )
                    )
                }
            })
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
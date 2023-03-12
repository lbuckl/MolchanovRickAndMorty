package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.molchanov.domain.character.Character
import com.molchanov.domain.character.CharacterPage
import com.molchanov.molchanovrickandmorty.data.networkstatus.INetworkStatus
import com.molchanov.molchanovrickandmorty.ui.base.BaseViewModel
import com.molchanov.repository.cases.ILocalRequest
import com.molchanov.repository.cases.IRemoteRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

/**
 * ViewModel реализующая бизнес-логику:
 * - обращение к API и БД
 * - определение состояний для CharactersFragment
 */
class CharactersViewModel: BaseViewModel<CharactersAppState>() {

    private val outLiveData: LiveData<CharactersAppState> by lazy {
        liveData
    }

    //Задержка перед установкой нового AppState
    private val appStateDelay = 100L

    private var lastPageActual = 1

    //region инициализация переменных
    @Inject
    @Named("CharacterRepoRemote")
    lateinit var repoRemote: IRemoteRequest<Int, String, CharacterPage, Character>

    @Inject
    @Named("CharacterRepoLocal")
    lateinit var repoLocal: ILocalRequest<Int, Int, CharacterPage, Character>

    @Inject
    lateinit var networkStatus: INetworkStatus

    private val disposable: CompositeDisposable = CompositeDisposable()
    //endregion

    fun getMyLiveData(): LiveData<CharactersAppState> {
        if (liveData.value == null) getData(1)
        return outLiveData
    }

    //Основная функция запроса данных
    @SuppressLint("CheckResult")
    fun getData(page: Int){
        loadingState(true)

        lastPageActual = page

        networkStatus.isOnlineSingle()
            .subscribeOn(Schedulers.single())
            .subscribeWith(object : SingleObserver<Boolean>{

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    liveData.postValue(CharactersAppState.Error("Network"))
                }

                override fun onSuccess(t: Boolean) {
                    if (t) disposable.add(
                        repoRemote.getData(page)
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                        {
                            liveData.postValue(CharactersAppState.Success(it))

                            repoLocal.saveData(it, page)

                            Thread.sleep(200)

                            loadingState(false)
                        },
                        {
                            liveData.postValue(
                                CharactersAppState.Error("Try get data from cache")
                            )

                            reserveDbRequest(page)
                        }
                        )
                    )
                    else reserveDbRequest(page)
                }
            })
    }


    //Перезгрузка данных
    fun reloadData(){
        getData(lastPageActual)
    }


    //Резервный запрос в БД
    private fun reserveDbRequest(page: Int){
        disposable.add(
            repoLocal.getData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        liveData.value =  CharactersAppState.Success(it)
                    },
                    {
                        liveData.value =
                            CharactersAppState.Error("No data in DataBase")
                    }
                )
        )

        loadingState(false)
    }


    //Функция для утсановки состояния загрузки
    private fun loadingState(state: Boolean){

        Thread.sleep(appStateDelay)

        liveData.postValue(CharactersAppState.Loading(state))
    }

    fun getCharacterInfo(character: Character){
        liveData.postValue(CharactersAppState.SuccessCharacter(character))
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
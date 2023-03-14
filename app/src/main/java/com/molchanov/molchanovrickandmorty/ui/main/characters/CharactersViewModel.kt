package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.annotation.SuppressLint
import android.util.Log
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
    lateinit var repoLocal: ILocalRequest<Int, Int, String, CharacterPage, Character>

    @Inject
    lateinit var networkStatus: INetworkStatus

    private var disposable: Disposable = Disposable.empty()

    private var networkStatusResult: Boolean? = null

    private var networkStatusInit = false
    //endregion

    fun getMyLiveData(): LiveData<CharactersAppState> {

        if (!networkStatusInit) initNetWorkObserver()

        if (liveData.value == null) getData(1)

        return outLiveData
    }

    private fun initNetWorkObserver(){

        networkStatus.isOnline().subscribe(
            {
                networkStatusResult = it
            },
            {
                networkStatusResult = null
            }
        )

        networkStatusInit = true
    }

    fun getData(page: Int){
            when(networkStatusResult) {
                true -> {
                        lastPageActual = page

                        disposable = repoRemote.getData(page)
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                {
                                    liveData.postValue(CharactersAppState.Loading(false))

                                    postStateDelayed(CharactersAppState.Success(it))

                                    repoLocal.saveData(it, page)
                                },
                                {
                                    reserveGetRequest(page)
                                }
                            )
                }
                else -> reserveGetRequest(page)

            }
    }

    //Резервный запрос в БД
    private fun reserveGetRequest(page: Int){

        disposable = repoLocal.getData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        liveData.value = CharactersAppState.Loading(false)

                        postStateDelayed(CharactersAppState.Success(it))
                    },
                    {
                        liveData.value =
                            CharactersAppState.Error("No data in DataBase")
                    }
                )
    }

    fun searchData(searchWord: String){

        when(networkStatusResult) {
            true -> {
                disposable = repoRemote.getSearchedData(lastPageActual, searchWord)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            liveData.postValue(CharactersAppState.Loading(false))

                            postStateDelayed(CharactersAppState.Success(it))
                        },
                        {
                            reserveSearchRequest(searchWord)
                        }
                    )
            }
            else -> reserveSearchRequest(searchWord)
        }
    }

    //Резервный запрос в БД
    private fun reserveSearchRequest(searchWord: String){

        disposable = repoLocal.getSearchedData(lastPageActual, searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveData.value = CharactersAppState.Loading(false)

                    postStateDelayed(CharactersAppState.Success(it))
                },
                {
                    liveData.value =
                        CharactersAppState.Error("No data in DataBase")
                }
            )
    }

    private fun postStateDelayed(state: CharactersAppState){
        Thread.sleep(appStateDelay)

        liveData.postValue(state)
    }

    //Перезгрузка данных
    fun reloadData(){
        getData(lastPageActual)
    }

    fun getCharacterInfo(character: Character){
        liveData.postValue(CharactersAppState.SuccessCharacter(character))
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
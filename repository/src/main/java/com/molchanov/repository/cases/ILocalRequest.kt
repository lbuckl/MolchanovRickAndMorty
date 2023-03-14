package com.molchanov.repository.cases

import com.molchanov.repository.cases.primitive.IGetData
import com.molchanov.repository.cases.primitive.IGetSearchedData
import com.molchanov.repository.cases.primitive.ISaveData
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для реализации взаимодействия с БД
 */
interface ILocalRequest <KEY, ID, IDS, DATA: Any, D: Any>:

    IGetData<KEY, Single<DATA>>,
    IGetSearchedData<KEY, IDS, Single<DATA>>,
    ISaveData<DATA, KEY>

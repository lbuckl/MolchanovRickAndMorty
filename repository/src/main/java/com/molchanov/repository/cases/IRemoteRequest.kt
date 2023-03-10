package com.molchanov.repository.cases

import com.molchanov.repository.cases.primitive.IGetData
import com.molchanov.repository.cases.primitive.IGetDetailInfo
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для реализации взаимодействия с API
 */
interface IRemoteRequest <KEY, ID, DATA : Any, D: Any>:

    IGetData<KEY, Single<DATA>>,
    IGetDetailInfo<ID, Single<List<D>>>
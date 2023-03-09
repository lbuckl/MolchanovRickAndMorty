package com.molchanov.repository.cases

import com.molchanov.repository.cases.primitive.IGetData
import com.molchanov.repository.cases.primitive.IGetDetailInfo
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для реализации взаимодействия с API
 */
interface IRemoteRequest <K, L, V : Any, T: Any>:

    IGetData<K, Single<V>>,
    IGetDetailInfo<L, Single<List<T>>>
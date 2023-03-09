package com.molchanov.repository.cases

import com.molchanov.repository.cases.primitive.IGetData
import com.molchanov.repository.cases.primitive.IGetDetailInfo
import com.molchanov.repository.cases.primitive.ISaveData
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для реализации взаимодействия с БД
 */
interface ILocalRequest <K, L, V: Any, T : Any>:

    IGetData<K, Single<V>>,
    IGetDetailInfo<L, Single<List<T>>>,
    ISaveData<V, K>

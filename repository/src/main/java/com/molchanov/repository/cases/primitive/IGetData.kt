package com.molchanov.repository.cases.primitive

/**
 * Интерфейс для получения основных данных
 */
interface IGetData<V, T> {
    fun getData(requestData: V) : T
}
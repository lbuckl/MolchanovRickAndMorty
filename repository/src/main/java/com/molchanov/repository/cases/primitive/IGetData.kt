package com.molchanov.repository.cases.primitive

/**
 * Интерфейс для получения основных данных
 */
interface IGetData<IN, OUT> {
    fun getData(requestData: IN) : OUT
}
package com.molchanov.repository.cases.primitive

/**
 * Интерфейс для сохранения данных в БД
 */
interface ISaveData<V, K> {
    fun saveData(data: V, key: K)
}
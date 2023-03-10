package com.molchanov.repository.cases.primitive

/**
 * Интерфейс для сохранения данных в БД
 */
interface ISaveData<IN, KEY> {
    fun saveData(data: IN, key: KEY)
}
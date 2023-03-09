package com.molchanov.repository.cases.primitive

/**
 * Интерфейс для получения детальной информации
 */
interface IGetDetailInfo<V, T> {
    fun getDetailInfo(id: V) : T
}
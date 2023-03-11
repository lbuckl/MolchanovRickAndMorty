package com.molchanov.repository.cases.primitive

/**
 * Интерфейс для получения детальной информации
 */
interface IGetDetailInfo<IN, OUT> {
    fun getDetailInfo(id: IN) : OUT
}
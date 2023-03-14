package com.molchanov.repository.cases.primitive

interface IGetSearchedData<IN, KEY, OUT> {
    fun getSearchedData(requestData: IN, searchWord: KEY) : OUT
}
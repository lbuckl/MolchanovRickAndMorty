package com.molchanov.repository

import com.molchanov.repository.cases.ICharacterRequest
import com.molchanov.repository.cases.IEpisodesRequest
import com.molchanov.repository.cases.ILocationRequest

interface IRepository:
    ICharacterRequest,
    ILocationRequest,
    IEpisodesRequest
package com.molchanov.repository.remote

import com.molchanov.repository.cases.ICharacterRequest
import com.molchanov.repository.cases.IEpisodesRequest
import com.molchanov.repository.cases.ILocationRequest

interface IRepositoryRemote:
    ICharacterRequest,
    ILocationRequest,
    IEpisodesRequest
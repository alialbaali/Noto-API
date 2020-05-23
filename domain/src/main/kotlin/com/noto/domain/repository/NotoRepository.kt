package com.noto.domain.repository

import com.noto.domain.model.Noto

interface NotoRepository {

    suspend fun createNoto(noto: Noto)

    suspend fun deleteNoto(notoId: Long)

    suspend fun updateNoto(noto: Noto)

    suspend fun getNotos(userId: Long, libraryId: Long) : Result<List<Noto>>
}
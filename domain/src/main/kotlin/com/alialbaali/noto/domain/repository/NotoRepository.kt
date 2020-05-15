package com.alialbaali.noto.domain.repository

import com.alialbaali.noto.domain.model.Noto

interface NotoRepository {

    suspend fun createNoto(noto: Noto)

    suspend fun deleteNoto(notoId: Long)

    suspend fun updateNoto(noto: Noto)

    suspend fun getNotos(libraryId: Long) : Result<List<Noto>>

    suspend fun getNoto(notoId: Long): Result<Noto>
}
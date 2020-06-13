package com.noto.data.source

import com.noto.domain.model.Noto

interface NotoDataSource {

    suspend fun createNoto(userId: Long, noto: Noto)

    suspend fun getNotos(userId: Long): List<Noto>

    suspend fun updateNoto(userId: Long, noto: Noto)

    suspend fun deleteNoto(userId: Long, libraryClientId: Long, notoId: Long)

    suspend fun getNotoByClientId(userId: Long, libraryClientId: Long, notoId: Long): Noto?
}
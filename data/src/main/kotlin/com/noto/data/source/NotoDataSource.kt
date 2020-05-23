package com.noto.data.source

import com.noto.domain.model.Noto

interface NotoDataSource {

    suspend fun createNoto(noto: Noto)

    suspend fun getNotos(userId: Long, libraryId: Long): List<Noto>

    suspend fun deleteNoto(notoId: Long)

    suspend fun updateNoto(noto: Noto)
}
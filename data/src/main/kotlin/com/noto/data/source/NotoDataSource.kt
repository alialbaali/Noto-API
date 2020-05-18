package com.noto.data.source

import com.noto.domain.model.Noto

interface NotoDataSource {

    suspend fun createNoto(noto: Noto)

    suspend fun getNotos(libraryId: Long): List<Noto>

    suspend fun getNotoById(notoId: Long): Noto

    suspend fun countLibraryNotos(libraryId: Long): Int

    suspend fun countNotos(): Int

    suspend fun deleteNoto(notoId: Long)

    suspend fun updateNoto(noto: Noto)
}
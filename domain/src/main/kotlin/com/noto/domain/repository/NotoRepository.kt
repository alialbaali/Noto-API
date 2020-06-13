package com.noto.domain.repository

import com.noto.domain.model.Noto

interface NotoRepository {

    suspend fun createNoto(userId: Long, noto: Noto): Result<Noto>

    suspend fun updateNoto(userId: Long, noto: Noto): Result<Noto>

    suspend fun deleteNoto(userId: Long, libraryClientId: Long, notoId: Long): Result<Long>

    suspend fun getNotos(userId: Long): Result<List<Noto>>
}
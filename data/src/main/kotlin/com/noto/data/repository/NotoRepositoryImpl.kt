package com.noto.data.repository

import com.noto.data.source.NotoDataSource
import com.noto.domain.model.Noto
import com.noto.domain.repository.NotoRepository

class NotoRepositoryImpl(private val local: NotoDataSource) : NotoRepository {

    override suspend fun createNoto(noto: Noto) {
        local.createNoto(noto)
    }

    override suspend fun deleteNoto(notoId: Long) {
        local.deleteNoto(notoId)
    }

    override suspend fun updateNoto(noto: Noto) {
        local.updateNoto(noto)
    }

    override suspend fun getNotos(userId: Long, libraryId: Long): Result<List<Noto>> {
        return Result.success(local.getNotos(userId, libraryId))
    }
}
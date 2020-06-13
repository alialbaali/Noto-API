package com.noto.data.repository

import com.noto.data.source.NotoDataSource
import com.noto.domain.model.Noto
import com.noto.domain.repository.NotoRepository

class NotoRepositoryImpl(private val source: NotoDataSource) : NotoRepository {

    override suspend fun createNoto(userId: Long, noto: Noto): Result<Noto> {
        return if (source.getNotoByClientId(userId, noto.libraryId, noto.notoId) == null) {
            source.createNoto(userId, noto)
            Result.success(noto)
        } else {
            Result.success(noto)
        }
    }

    override suspend fun updateNoto(userId: Long, noto: Noto): Result<Noto> {
        return if (source.getNotoByClientId(userId, noto.libraryId, noto.notoId) == null) {
            Result.failure(Throwable("Library doesn't exist!"))
        } else {
            source.updateNoto(userId, noto)
            return Result.success(noto)
        }
    }

    override suspend fun deleteNoto(userId: Long, libraryClientId: Long, notoId: Long): Result<Long> {
        return if (source.getNotoByClientId(userId, libraryClientId, notoId) == null) {
            Result.failure(Throwable("Library doesn't exist!"))
        } else {
            source.deleteNoto(userId, libraryClientId, notoId)
            return Result.success(notoId)
        }
    }

    override suspend fun getNotos(userId: Long): Result<List<Noto>> {
        return Result.success(source.getNotos(userId))
    }
}
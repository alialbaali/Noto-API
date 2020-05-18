package com.noto.data.repository

import com.noto.data.source.NotoDataSource
import com.noto.domain.model.Noto
import com.noto.domain.repository.NotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotoRepositoryImpl(private val local: NotoDataSource) : NotoRepository {

    override suspend fun createNoto(noto: Noto) {
        withContext(Dispatchers.IO) {
            local.createNoto(noto)
        }
    }

    override suspend fun deleteNoto(notoId: Long) {
        withContext(Dispatchers.IO) {
            local.deleteNoto(notoId)
        }
    }

    override suspend fun updateNoto(noto: Noto) {
        withContext(Dispatchers.IO) {
            local.updateNoto(noto)
        }
    }

    override suspend fun getNotos(libraryId: Long): Result<List<Noto>> {

        return withContext(Dispatchers.IO) {
//            try {
                Result.success(local.getNotos(libraryId))
//            } catch (e: Throwable) {
//                Result.failure(e)
//            }
        }

    }

    override suspend fun getNoto(notoId: Long): Result<Noto> {
        return withContext(Dispatchers.IO) {
//            try {
                Result.success(local.getNotoById(notoId))
//            } catch (e: Throwable) {
//                Result.failure(e)
//            }
        }
    }
}
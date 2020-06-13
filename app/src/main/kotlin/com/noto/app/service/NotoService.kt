package com.noto.app.service

import com.noto.app.BadRequestException
import com.noto.domain.interactor.noto.NotoUseCases
import com.noto.domain.model.Noto

class NotoService(private val notoUseCases: NotoUseCases) {

    suspend fun createNoto(userId: Long, noto: Noto): Noto {

        return notoUseCases.createNoto(userId, noto).getOrElse { throwable ->
            throw BadRequestException(throwable.message)
        }

    }

    suspend fun getNotos(userId: Long): List<Noto> {

        return notoUseCases.getNotos(userId).getOrElse { throwable ->
            throw BadRequestException(throwable.message)
        }

    }

    suspend fun updateNoto(userId: Long, noto: Noto): Noto {

        return notoUseCases.updateNoto(userId, noto).getOrElse { throwable ->
            throw BadRequestException(throwable.message)
        }

    }

    suspend fun deleteNoto(userId: Long, libraryClientId: Long, notoId: Long): Long {

        return notoUseCases.deleteNoto(userId, libraryClientId, notoId).getOrElse { throwable ->
            throw BadRequestException(throwable.message)
        }

    }
}

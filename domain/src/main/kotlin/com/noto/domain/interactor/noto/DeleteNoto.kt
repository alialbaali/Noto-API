package com.noto.domain.interactor.noto

import com.noto.domain.repository.NotoRepository

class DeleteNoto(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(userId: Long, libraryClientId: Long, notoId: Long) = notoRepository.deleteNoto(userId, libraryClientId, notoId)

}
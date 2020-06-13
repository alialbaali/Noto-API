package com.noto.domain.interactor.noto

import com.noto.domain.model.Noto
import com.noto.domain.repository.NotoRepository

class CreateNoto(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(userId: Long, noto: Noto) = notoRepository.createNoto(userId, noto)

}
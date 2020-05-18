package com.noto.domain.interactor.noto

import com.noto.domain.model.Noto
import com.noto.domain.repository.NotoRepository

class DeleteNoto(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(notoId: Long) = notoRepository.deleteNoto(notoId)

}
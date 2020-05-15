package com.alialbaali.noto.domain.interactor.noto

import com.alialbaali.noto.domain.model.Noto
import com.alialbaali.noto.domain.repository.NotoRepository

class DeleteNoto(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(notoId: Long) = notoRepository.deleteNoto(notoId)

}
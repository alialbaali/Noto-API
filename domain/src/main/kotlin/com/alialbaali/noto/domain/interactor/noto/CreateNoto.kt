package com.alialbaali.noto.domain.interactor.noto

import com.alialbaali.noto.domain.model.Noto
import com.alialbaali.noto.domain.repository.NotoRepository

class CreateNoto(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(noto: Noto) = notoRepository.createNoto(noto)

}
package com.alialbaali.noto.domain.interactor.noto

import com.alialbaali.noto.domain.repository.NotoRepository

class GetNotoById(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(notoId: Long) = notoRepository.getNoto(notoId)

}
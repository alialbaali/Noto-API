package com.noto.domain.interactor.noto

import com.noto.domain.repository.NotoRepository

class GetNotoById(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(notoId: Long) = notoRepository.getNoto(notoId)

}
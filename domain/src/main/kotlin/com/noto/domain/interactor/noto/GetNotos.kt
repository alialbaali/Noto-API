package com.noto.domain.interactor.noto

import com.noto.domain.repository.NotoRepository

class GetNotos(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(userId: Long) = notoRepository.getNotos(userId)

}
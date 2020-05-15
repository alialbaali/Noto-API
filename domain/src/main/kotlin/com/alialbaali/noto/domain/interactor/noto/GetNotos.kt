package com.alialbaali.noto.domain.interactor.noto

import com.alialbaali.noto.domain.repository.NotoRepository

class GetNotos(private val notoRepository: NotoRepository) {

    suspend operator fun invoke(libraryId: Long) = notoRepository.getNotos(libraryId)

}
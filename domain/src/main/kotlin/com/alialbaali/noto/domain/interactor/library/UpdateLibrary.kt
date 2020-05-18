package com.alialbaali.noto.domain.interactor.library

import com.alialbaali.noto.domain.model.Library
import com.alialbaali.noto.domain.repository.LibraryRepository

class UpdateLibrary(private val libraryRepository: LibraryRepository) {

    suspend operator fun invoke(userId: Long, library: Library) = libraryRepository.updateLibrary(userId, library)

}
package com.noto.domain.interactor.library

import com.noto.domain.model.Library
import com.noto.domain.repository.LibraryRepository

class UpdateLibrary(private val libraryRepository: LibraryRepository) {

    suspend operator fun invoke(userId: Long, library: Library) = libraryRepository.updateLibrary(userId, library)

}
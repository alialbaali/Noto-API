package com.noto.domain.interactor.library

import com.noto.domain.model.Library
import com.noto.domain.repository.LibraryRepository

class CreateLibrary(private val libraryRepository: LibraryRepository) {

    suspend operator fun invoke(userId: Long, library: Library) = libraryRepository.createLibrary(userId, library)

}
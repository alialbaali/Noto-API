package com.alialbaali.noto.domain.interactor.library

import com.alialbaali.noto.domain.model.Library
import com.alialbaali.noto.domain.repository.LibraryRepository

class CreateLibrary(private val libraryRepository: LibraryRepository) {

    suspend operator fun invoke(userId: Long ,library: Library) = libraryRepository.createLibrary(userId, library)

}
package com.alialbaali.noto.domain.interactor.library

import com.alialbaali.noto.domain.repository.LibraryRepository

class GetLibrary(private val libraryRepository: LibraryRepository) {

    suspend operator fun invoke(libraryId: Long) = libraryRepository.getLibraryById(libraryId)

}
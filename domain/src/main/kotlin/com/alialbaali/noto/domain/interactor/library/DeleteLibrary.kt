package com.alialbaali.noto.domain.interactor.library

import com.alialbaali.noto.domain.repository.LibraryRepository

class DeleteLibrary(private val libraryRepository: LibraryRepository) {

    suspend operator fun invoke(userId: Long, libraryId: Long) = libraryRepository.deleteLibrary(userId, libraryId)

}
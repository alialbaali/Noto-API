package com.alialbaali.noto.domain.interactor.library

import com.alialbaali.noto.domain.model.Library
import com.alialbaali.noto.domain.repository.LibraryRepository

class GetLibraries(private val libraryRepository: LibraryRepository) {

    suspend operator fun invoke(): Result<List<Library>> = libraryRepository.getLibraries()

}
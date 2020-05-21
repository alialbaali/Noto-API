package com.noto.app.service

import com.noto.app.BadRequestException
import com.noto.domain.interactor.library.LibraryUseCases
import com.noto.domain.model.Library

class LibraryService(private val libraryUseCases: LibraryUseCases) {

    suspend fun getLibraries(userId: Long): List<Library> {

        return libraryUseCases.getLibraries(userId).getOrElse {
            throw BadRequestException(it.message)
        }
    }

    suspend fun createLibrary(userId: Long, library: Library): Library {

        return libraryUseCases.createLibrary(userId, library).getOrElse {
            throw BadRequestException(it.message)
        }
    }

    suspend fun updateLibrary(userId: Long, library: Library): Library {

        return libraryUseCases.updateLibrary(userId, library).getOrElse {
            throw BadRequestException(it.message)
        }
    }

    suspend fun deleteLibrary(userId: Long, libraryId: Long): Long {

        return libraryUseCases.deleteLibrary(userId, libraryId).getOrElse {
            throw BadRequestException(it.message)
        }
    }
}
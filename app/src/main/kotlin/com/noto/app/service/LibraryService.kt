package com.noto.app.service

import com.noto.app.BadRequestException
import com.noto.domain.interactor.library.LibraryUseCases
import com.noto.domain.model.Library
import com.noto.domain.model.NotoColor
import com.noto.domain.model.NotoIcon

class LibraryService(private val libraryUseCases: LibraryUseCases) {

    suspend fun getLibraries(userId: Long): List<Library> {

        return libraryUseCases.getLibraries(userId).getOrElse { throwable ->
            throw BadRequestException(throwable.message)
        }
    }

    suspend fun createLibrary(userId: Long, library: Library): Library {

        return libraryUseCases.createLibrary(userId, library).getOrElse { throwable ->
            throw  BadRequestException(throwable.message)
        }

    }

    suspend fun updateLibrary(userId: Long, library: Library): Library {

        return libraryUseCases.updateLibrary(userId, library).getOrElse { throwable ->
            throw BadRequestException(throwable.message)
        }

    }

    suspend fun deleteLibrary(userId: Long, libraryId: Long): Long {

        return libraryUseCases.deleteLibrary(userId, libraryId).getOrElse { throwable ->
            throw BadRequestException(throwable.message)
        }

    }
}
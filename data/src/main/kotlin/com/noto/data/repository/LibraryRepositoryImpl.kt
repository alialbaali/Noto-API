package com.noto.data.repository

import com.noto.data.source.LibraryDataSource
import com.noto.domain.model.Library
import com.noto.domain.repository.LibraryRepository

class LibraryRepositoryImpl(private val source: LibraryDataSource) : LibraryRepository {

    override suspend fun getLibraries(userId: Long): Result<List<Library>> {
        return Result.success(source.getLibraries(userId))
    }

    override suspend fun createLibrary(userId: Long, library: Library): Result<Library> {
        return if (source.getLibrary(userId, library) == null) {
            source.createLibrary(userId, library)
            Result.success(library)
        } else {
            Result.success(library)
        }
    }

    override suspend fun updateLibrary(userId: Long, library: Library): Result<Library> {
        return if(source.getLibrary(userId, library) == null) {
            Result.failure(Throwable("Library doesn't exist!"))
        } else {
            source.updateLibrary(userId, library)
            Result.success(library)
        }
    }


    override suspend fun deleteLibrary(userId: Long, libraryId: Long): Result<Long> {
        source.deleteLibrary(userId, libraryId)
        return Result.success(libraryId)
    }
}
package com.noto.data.repository

import com.noto.data.source.LibraryDataSource
import com.noto.domain.model.Library
import com.noto.domain.repository.LibraryRepository

class LibraryRepositoryImpl(private val source: LibraryDataSource) : LibraryRepository {
    override suspend fun getLibraries(userId: Long): Result<List<Library>> {
        return Result.success(source.getLibraries(userId))
    }

    override suspend fun createLibrary(userId: Long, library: Library): Result<Library> {
        source.createLibrary(userId, library)
        return Result.success(library)
    }

    override suspend fun updateLibrary(userId: Long, library: Library): Result<Library> {
        source.updateLibrary(userId, library)
        return Result.success(library)
    }

    override suspend fun deleteLibrary(userId: Long, libraryId: Long): Result<Long> {
        source.deleteLibrary(userId, libraryId)
        return Result.success(libraryId)
    }
}
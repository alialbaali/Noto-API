package com.alialbaali.noto.domain.repository

import com.alialbaali.noto.domain.model.Library

interface LibraryRepository {

    suspend fun createLibrary(library: Library)

    suspend fun deleteLibrary(libraryId: Long)

    suspend fun updateLibrary(library: Library)

    suspend fun getLibraries(): Result<List<Library>>

    suspend fun getLibraryById(libraryId: Long): Result<Library>

}
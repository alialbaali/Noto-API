package com.noto.data.source

import com.noto.domain.model.Library

interface LibraryDataSource {

    suspend fun getLibraries(userId: Long): List<Library>

    suspend fun createLibrary(userId: Long, library: Library)

    suspend fun updateLibrary(userId: Long, library: Library)

    suspend fun deleteLibrary(userId: Long, libraryId: Long)

}
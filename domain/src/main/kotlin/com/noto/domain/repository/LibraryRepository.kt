package com.noto.domain.repository

import com.noto.domain.model.Library

interface LibraryRepository {

    suspend fun getLibraries(userId: Long): Result<List<Library>>

    suspend fun createLibrary(userId: Long, library: Library): Result<Library>

    suspend fun updateLibrary(userId: Long, library: Library): Result<Library>

    suspend fun deleteLibrary(userId: Long, libraryId: Long): Result<Long>

}
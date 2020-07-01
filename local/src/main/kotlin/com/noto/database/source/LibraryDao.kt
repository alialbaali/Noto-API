package com.noto.database.source

import com.noto.data.source.LibraryDataSource
import com.noto.database.model.Libraries
import com.noto.domain.model.Library
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class LibraryDao : LibraryDataSource {

    override suspend fun getLibraries(userId: Long): List<Library> {

        return transaction {

            Libraries.select { Libraries.userId eq userId }.map {
                Library(
                    libraryId = it[Libraries.libraryClientId],
                    libraryTitle = it[Libraries.libraryTitle],
                    libraryPosition = it[Libraries.libraryPosition],
                    notoColor = it[Libraries.notoColor],
                    notoIcon = it[Libraries.notoIcon],
                    sortType = it[Libraries.sortType],
                    sortMethod = it[Libraries.sortMethod]
                )
            }

        }
    }

    override suspend fun createLibrary(userId: Long, library: Library) {

        transaction {

            Libraries.insert {
                it[this.userId] = userId
                it[libraryClientId] = library.libraryId
                it[libraryTitle] = library.libraryTitle
                it[libraryPosition] = library.libraryPosition
                it[notoColor] = library.notoColor
                it[notoIcon] = library.notoIcon
                it[sortMethod] = library.sortMethod
                it[sortType] = library.sortType
            }

        }
    }

    override suspend fun updateLibrary(userId: Long, library: Library) {

        transaction {

            Libraries.update({ (Libraries.userId eq userId) and (Libraries.libraryClientId eq library.libraryId) }) {
                it[libraryClientId] = library.libraryId
                it[libraryTitle] = library.libraryTitle
                it[libraryPosition] = library.libraryPosition
                it[notoColor] = library.notoColor
                it[notoIcon] = library.notoIcon
                it[sortMethod] = library.sortMethod
                it[sortType] = library.sortType
            }

        }

    }

    override suspend fun deleteLibrary(userId: Long, libraryId: Long) {

        transaction {

            Libraries.deleteWhere { (Libraries.userId eq userId) and (Libraries.libraryClientId eq libraryId) }

        }
    }

    override suspend fun getLibraryByClientId(userId: Long, libraryId: Long): Library? {

        return transaction {

            Libraries.select { (Libraries.userId eq userId) and (Libraries.libraryClientId eq libraryId) }.map {
                Library(
                    libraryId = it[Libraries.libraryClientId],
                    libraryTitle = it[Libraries.libraryTitle],
                    libraryPosition = it[Libraries.libraryPosition],
                    notoColor = it[Libraries.notoColor],
                    notoIcon = it[Libraries.notoIcon],
                    sortType = it[Libraries.sortType],
                    sortMethod = it[Libraries.sortMethod]
                )
            }.firstOrNull()

        }
    }
}

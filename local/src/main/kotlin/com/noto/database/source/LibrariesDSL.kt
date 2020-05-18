package com.noto.database.source

import com.noto.data.source.LibraryDataSource
import com.noto.database.model.Libraries
import com.noto.domain.model.Library
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class LibrariesDSL : LibraryDataSource {
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
                    sortMethod = it[Libraries.sortMethod],
                    libraryCreationDate = it[Libraries.libraryCreationDate]
                )
            }
        }
    }

    override suspend fun createLibrary(userId: Long, library: Library) {
        transaction {
//            val ftm = DateTimeFormat.shortDate()
//            val string = ftm.print(library.libraryCreationDate)
//            val date = ftm.parseDateTime(string)
            Libraries.insert {
                it[this.userId] = userId
                it[libraryClientId] = library.libraryId
                it[libraryTitle] = library.libraryTitle
                it[libraryPosition] = library.libraryPosition
                it[notoColor] = library.notoColor
                it[notoIcon] = library.notoIcon
                it[sortMethod] = library.sortMethod
                it[sortType] = library.sortType
                it[libraryCreationDate] = library.libraryCreationDate
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
                it[libraryCreationDate] = library.libraryCreationDate
            }
        }
    }

    override suspend fun deleteLibrary(userId: Long, libraryId: Long) {
        transaction {
            Libraries.deleteWhere { (Libraries.userId eq userId) and (Libraries.libraryId eq libraryId) }
        }
    }
}
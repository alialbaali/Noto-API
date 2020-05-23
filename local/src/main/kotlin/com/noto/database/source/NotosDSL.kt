package com.noto.database.source

import com.noto.data.source.NotoDataSource
import com.noto.database.model.Libraries
import com.noto.database.model.Notos
import com.noto.domain.model.Noto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


class NotosDSL : NotoDataSource {
    override suspend fun createNoto(userId: Long, noto: Noto) {

        val notoLibraryId =
            (Libraries innerJoin Notos).select { (Libraries.userId eq userId) and (Libraries.libraryClientId eq noto.libraryId) }
                .map {
                    it[Libraries.libraryId]
                }.first()

        transaction {
            Notos.insert {
                it[notoClientId] = noto.notoId
                it[libraryId] = notoLibraryId
                it[notoTitle] = noto.notoTitle
                it[notoBody] = noto.notoBody
                it[notoPosition] = noto.notoPosition
                it[notoIsStarred] = noto.notoIsStarred
                it[notoCreationDate] = noto.notoCreationDate
                it[notoReminder] = noto.notoReminder
            }
        }
    }

    override suspend fun getNotos(userId: Long, libraryId: Long): List<Noto> {
        return transaction {
            (Libraries innerJoin Notos).select { (Libraries.userId eq userId) and (Libraries.libraryClientId eq Notos.libraryId) }
                .map {
                    Noto(
                        it[Notos.notoClientId],
                        it[Libraries.libraryClientId],
                        it[Notos.notoTitle],
                        it[Notos.notoBody],
                        it[Notos.notoPosition],
                        it[Notos.notoCreationDate],
                        it[Notos.notoIsStarred],
                        it[Notos.notoReminder]
                    )
                }
        }
    }

    override suspend fun deleteNoto(notoId: Long) {
        transaction {
            Notos.deleteWhere { Notos.notoId eq notoId }
        }
    }

    override suspend fun updateNoto(noto: Noto) {
        transaction {
            Notos.update {
                it[notoClientId] = noto.notoId
                it[libraryId] = noto.libraryId
                it[notoTitle] = noto.notoTitle
                it[notoBody] = noto.notoBody
                it[notoPosition] = noto.notoPosition
                it[notoIsStarred] = noto.notoIsStarred
                it[notoReminder] = noto.notoReminder
            }
        }
    }

}
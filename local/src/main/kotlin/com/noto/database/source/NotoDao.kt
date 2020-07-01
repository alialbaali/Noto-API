package com.noto.database.source

import com.noto.data.source.NotoDataSource
import com.noto.database.model.Libraries
import com.noto.database.model.Notos
import com.noto.domain.model.Noto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


class NotoDao : NotoDataSource {

    override suspend fun createNoto(userId: Long, noto: Noto) {

        transaction {

            Notos.insert {
                it[notoClientId] = noto.notoId
                it[this.libraryId] = getLibraryId(userId, noto.libraryId)
                it[notoTitle] = noto.notoTitle
                it[notoBody] = noto.notoBody
                it[notoPosition] = noto.notoPosition
                it[notoIsStarred] = noto.notoIsStarred
            }

        }
    }

    override suspend fun getNotos(userId: Long): List<Noto> {

        return transaction {
            (Libraries innerJoin Notos).select { (Libraries.userId eq userId) }
                .map {
                    Noto(
                        it[Notos.notoClientId],
                        it[Notos.libraryId],
                        it[Notos.notoTitle],
                        it[Notos.notoBody],
                        it[Notos.notoPosition],
                        it[Notos.notoIsStarred]
                    )
                }
        }
    }

    private fun getLibraryId(userId: Long, libraryClientId: Long): Long {
        return transaction {
            Libraries.select { (Libraries.userId eq userId) and (Libraries.libraryClientId eq libraryClientId) }
                .map {
                    it[Libraries.libraryId]
                }.first()
        }
    }

    override suspend fun updateNoto(userId: Long, noto: Noto) {
        transaction {

            val libraryId = getLibraryId(userId, noto.libraryId)

            Notos.update({ (Notos.libraryId eq libraryId) and (Notos.notoClientId eq noto.notoId) }) {
                it[notoClientId] = noto.notoId
                it[this.libraryId] = libraryId
                it[notoTitle] = noto.notoTitle
                it[notoBody] = noto.notoBody
                it[notoPosition] = noto.notoPosition
                it[notoIsStarred] = noto.notoIsStarred
            }
        }
    }

    override suspend fun deleteNoto(userId: Long, libraryClientId: Long, notoId: Long) {
        transaction {
            val libraryId = getLibraryId(userId, libraryClientId)

            Notos.deleteWhere { (Notos.libraryId eq libraryId) and (Notos.notoClientId eq notoId) }
        }
    }

    override suspend fun getNotoByClientId(userId: Long, libraryClientId: Long, notoId: Long): Noto? {
        return transaction {
            val libraryId = getLibraryId(userId, libraryClientId)
            (Libraries innerJoin Notos).select { (Libraries.userId eq userId) and (Notos.libraryId eq libraryId) and (Notos.notoClientId eq notoId) }
                .map {
                    Noto(
                        it[Notos.notoClientId],
                        it[Notos.libraryId],
                        it[Notos.notoTitle],
                        it[Notos.notoBody],
                        it[Notos.notoPosition],
                        it[Notos.notoIsStarred]
                    )
                }.firstOrNull()
        }
    }
}
